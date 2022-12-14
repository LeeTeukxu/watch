!function (t, i) {
    "object" == typeof exports && "undefined" != typeof module ? module.exports = i() : "function" == typeof define && define.amd ? define(i) : t.iNotify = i()
}
(this, function (t, i) {
    function e(t) {
        t && this.init(t)
    }
    function n(t) {
        var i = document.querySelectorAll("link[rel~=shortcut]")[0];
        return i || (i = c("O", t)),
            i
    }
    function o(t) {
        var i,
            e = document.createElement("audio");
        if (r(t) && t.length > 0)
            for (var n = 0; n < t.length; n++)
                i = document.createElement("source"), i.src = t[n], i.type = "audio/" + s(t[n]), e.appendChild(i);
        else
            e.src = t;
        return e
    }
    function r(t) {
        return "[object Array]" === Object.prototype.toString.call(t)
    }
    function c(t, i) {
        var e,
            n = document.createElement("canvas"),
            o = (document.createElement("img"), document.getElementsByTagName("head")[0]),
            r = document.createElement("link");
        return n.height = n.width = 32,
            e = n.getContext("2d"),
            e.fillStyle = i.backgroundColor,
            e.fillRect(0, 0, 32, 32),
            e.textAlign = "center",
            e.font = '22px "helvetica", sans-serif',
            e.fillStyle = i.textColor,
            e.fillText(t, 16, 24),
            r.setAttribute("rel", "shortcut icon"),
            r.setAttribute("type", "image/x-icon"),
            r.setAttribute("id", "new" + i.id),
            r.setAttribute("href", n.toDataURL("image/png")),
            h = n.toDataURL("image/png"),
            o.appendChild(r)
    }
    function s(t) {
        return t.match(/\.([^\.]+)$/)[1]
    }
    function a(t, i) {
        for (var e in i)
            t[e] && (i[e] = t[e]);
        return i
    }
    var l = ["flash", "scroll"],
        u = {
            title: "通知！",
            body: "您来了一条新消息",
            openurl: ""
        },
        h = "";
    return e.prototype = {
        init: function (t) {
            return t || (t = {}),
                this.interval = t.interval || 200,
                this.effect = t.effect || "flash",
                this.title = t.title || document.title,
                this.message = t.message || this.title,
                this.onclick = t.onclick || this.onclick,
                this.openurl = t.openurl || this.openurl,
                this.updateFavicon = t.updateFavicon || {
                    id: "favicon",
                    textColor: "#fff",
                    backgroundColor: "#2F9A00"
                },
                this.audio = t.audio || "",
                this.favicon = n(this.updateFavicon),
                this.favicon = this.favicon,
                this.cloneFavicon = this.favicon.cloneNode(!0),
                u.icon = h = t.notification && t.notification.icon ? t.notification.icon : t.icon ? t.icon : this.favicon.href,
                this.notification = t.notification || u,
            this.audio && this.audio.file && this.setURL(this.audio.file),
                this
        },
        render: function () {
            switch (this.effect) {
                case "flash":
                    document.title = this.title === document.title ? this.message : this.title;
                    break;
                case "scroll":
                    document.title = document.title.slice(1),
                    0 === document.title.length && (document.title = this.message)
            }
        },
        setURL: function (t) {
            return t && (this.audioElm && this.audioElm.remove(), this.audioElm = o(t), document.body.appendChild(this.audioElm)),
                this
        },
        loopPlay: function () {
            return this.setURL(),
                this.audioElm.loop = !0,
                this.player(),
                this
        },
        stopPlay: function () {
            return this.audioElm && (this.audioElm.loop = !1, this.audioElm.pause()),
                this
        },
        player: function () {
            this.audio.file;
            if (this.audio && this.audio.file)
                return this.audioElm || (this.audioElm = o(this.audio.file), document.body.appendChild(this.audioElm)), this.audioElm.play(), this
        },
        notify: function (t) {
            var i = this.notification,
                e = t.openurl ? t.openurl : this.openurl,
                n = t.onclick ? t.onclick : this.onclick;
            if (window.Notification) {
                i = t ? a(t, i) : u;
                var o = {};
                o.icon = t.icon ? t.icon : h,
                    o.body = i.body,
                t.dir && (o.dir = t.dir);
                var r = new Notification(i.title, o);
                r.onclick = function () {
                    n && "function" == typeof n && n(r),
                    e && window.open(e)
                },
                    r.onshow = function () {
                        t.onshow && "function" == typeof t.onshow && t.onshow(r)
                    },
                    r.onclose = function () {
                        t.onclose && "function" == typeof t.onclose && t.onclose(r)
                    },
                    r.onerror = function () {
                        t.onerror && "function" == typeof t.onerror && t.onerror(r)
                    }
            }
            return this
        },
        isPermission: function () {
            return !(!window.Notification || "granted" !== Notification.permission)
        },
        setTitle: function (t) {
            if (t === !0) {
                if (0 <= l.indexOf(this.effect))
                    return this.addTimer()
            } else
                t ? (this.message = t, this.addTimer()) : (this.clearTimer(), this.title = this.title);
            return this
        },
        setInterval: function (t) {
            return t && (this.interval = t, this.addTimer()),
                this
        },
        setFavicon: function (t) {
            if (!t && 0 !== t)
                return this.faviconClear();
            var i = document.getElementById("new" + this.updateFavicon.id);
            return this.favicon && this.favicon.remove(),
            i && i.remove(),
                c(t, this.updateFavicon),
                this
        },
        addTimer: function () {
            return this.clearTimer(),
            0 <= l.indexOf(this.effect) && (this.timer = setInterval(this.render.bind(this), this.interval)),
                this
        },
        faviconClear: function () {
            var t = document.getElementById("new" + this.updateFavicon.id),
                i = document.getElementsByTagName("head")[0],
                e = document.querySelectorAll("link[rel~=shortcut]");
            if (t && t.remove(), e.length > 0)
                for (var n = 0; n < e.length; n++)
                    e[n].remove();
            return i.appendChild(this.cloneFavicon),
                h = this.cloneFavicon.href,
                this.favicon = this.cloneFavicon,
                this
        },
        clearTimer: function () {
            return clearInterval(this.timer),
                document.title = this.title,
                this
        }
    },
    window.Notification && "granted" !== window.Notification.permission && window.Notification.requestPermission(),
        e
});