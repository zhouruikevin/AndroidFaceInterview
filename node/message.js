class IncomingMessage {
    constructor(raw) {
        try {
            this._raw = JSON.parse(raw)
        } catch (error) {
            throw new Error("Cannot parse incoming message: " + error.message)
        }
    }

    get raw() {
        return this._raw
    }

    get groupID() {
        if (this.raw.groupid) {
            return this.raw.groupid
        }
        throw new Error("no group id in message")
    }

    get callerUserID() {
        let message = this.raw.message
        if (message && message.header && message.header.fromuserid) {
            return message.header.fromuserid
        }
        throw new Error("no caller user id in message")
    }

    get rawBody() {
        let message = this.raw.message
        if (message && message.body) {
            return message.body
        }
        throw new Error("no body in message")
    }
}

module.exports = {
    IncomingMessage
}