const querystring = require('querystring')
const HiRobot = require('./robot')
const config = require("./config")

exports.handler = async (event, context) => {
    // Hi验证回调接口使用
    const queryStringBody = querystring.parse(event.body)
    if (queryStringBody['echostr']) {
        return {
            "isBase64Encoded": false,
            "statusCode": 200,
            "body": queryStringBody['echostr']
        }
    }

    // 初始化机器人
    const robot = new HiRobot(config.OutEndpoint, config.Token, config.EncodingAESKey)

    // 传入 event.body 解析接收到的消息
    const incoming = robot.receiveMessage(event.body)
    const caller = incoming.callerUserID
    const text = incoming.rawBody[1].content
    const hand = text.toLowerCase()

    // 拼装返回的消息
    let messageBody = [{
        "atuserids": [caller],
        "atall": false,
        "type": "AT"
    }, {
        content: text,
        type: "TEXT"
    }, {
        content: "这个功能RD还在开发中...\n",
        type: "TEXT"
    }]

    if (hand.indexOf("android") != -1) {
        messageBody = [{
            "atuserids": [caller],
            "atall": false,
            "type": "AT"
        }, {
            content: "android最新包地址\n",
            type: "TEXT"
        }, {
            "href": "http://d.7short.com/bnz3",
            "type": "LINK"
        }]
    } else if (hand.indexOf("ios") != -1) {
        messageBody = [{
            "atuserids": [caller],
            "atall": false,
            "type": "AT"
        }, {
            content: "ios最新包地址\n",
            type: "TEXT"
        }, {
            "href": "http://d.7short.com/zjp8",
            "type": "LINK"
        }]
    }

    // 发送消息
    try {
        await robot.sendMessage(incoming.groupID, messageBody)
    } catch (error) {
        console.error(error)
        return {
            "isBase64Encoded": false,
            "statusCode": 500,
            "body": error.message
        }
    }

    return {
        "isBase64Encoded": false,
        "statusCode": 200,
        "body": "done"
    }

}