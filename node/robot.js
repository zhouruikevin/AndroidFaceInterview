const crypto = require("crypto")
const http = require("http")
const IncomningMessage = require("./message").IncomingMessage

class HiRobot {
    constructor(sendEndpoint, token, encodingAesKey) {
        this.sendEndpoint = sendEndpoint;
        this.token = token;
        this.encodingAesKey = encodingAesKey
    }

    async sendMessage(groupID, body) {
        groupID = parseInt(groupID, 10)
        const message = {
            "message": {
                "header": {
                    "toid": [groupID]
                },
                "body": body
            }
    
        }
        const postBody = safeJsonStringify(message)
    
        return new Promise((resolve, reject) => {
            const req = http.request(this.sendEndpoint, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8',
                    'Content-Length': postBody.length
                }
            }, res => {
                if (res.statusCode < 200 || res.statusCode >= 300) {
                    return reject(new Error(`Status Code: ${res.statusCode}`));
                }
    
                const chunkRes = [];
    
                res.on('data', chunk => {
                    chunkRes.push(chunk);
                });
    
                res.on('end', () => {
                    const responseStr = Buffer.concat(chunkRes).toString()
                    let response
                    try {
                        response = JSON.parse(responseStr)
                    } catch (e) {
                        reject(e)
                    }
                    if (response.errcode === 0 && response.errmsg === 'ok') {
                        resolve(response)
                    } else {
                        reject(response)
                    }
                })
            })
    
            req.on('error', reject)
    
            req.write(postBody)
            req.end()
        })
    
    }

    receiveMessage(strBody) {
        const decipher = crypto.createDecipheriv('aes-128-ecb', base64UrlDecode(this.encodingAesKey), '')
        let decrypted
        try {
            decrypted = Buffer.concat([decipher.update(base64UrlDecode(strBody)), decipher.final()]).toString('utf-8')
        } catch (error) {
            throw new Error("cannot decrpyt message: "+ error.message)
        }
        return new IncomningMessage(decrypted)
    }
}

function safeJsonStringify(v) {
    return JSON.stringify(v).replace(/[\u007f-\uffff]/g,
        function (c) {
            return '\\u' + ('000' + c.charCodeAt(0).toString(16)).slice(-4);
        }
    );
}

function base64UrlDecode(encoded) {
    encoded = encoded.replace('-', '+').replace('_', '/')
    while (encoded.length % 4) {
        encoded += '='
    }
    return Buffer.from(encoded || '', 'base64')
}

module.exports = HiRobot