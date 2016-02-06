var ACCESS_TOKEN = null,
    USER = null;


/*
// Funzione di utilità: 
Ext.define('Override.data.Proxy', {
    override : 'Ext.data.Proxy',
    listeners: {
        exception : function() {
            Ext.Msg.show({
                title: 'Errore inaspettato',
                msg: "C'è stato un errore durante il caricamento dei dati. Riprovare. \n Se l'errore persiste contattare l'assistenza.",
                width: 300,
                buttons: Ext.Msg.OK,
                icon: Ext.MessageBox.ERROR
            });
        }
    }
});
*/



var daysBetween = function(timeStampA, timeStampB) {
    var oneDay = 24 * 60 * 60 * 1000; // hours * minutes * seconds * milliseconds
    var firstDate = new Date(timeStampA * 1000);
    var secondDate = new Date(timeStampB * 1000);
    var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
    return diffDays;
};




download = function(config, id) {
    var form,
        removeNode = download.removeNode,
        frameId = id,

        iframe = Ext.core.DomHelper.append(document.body, {
            id: frameId,
            name: frameId,
            style: 'display:none',
            tag: 'iframe'
        }),

        inputs = paramsToInputs(config.params);


    // If the download succeeds the load event won't fire but it can in the failure case. We avoid using Ext.Element on
    // the iframe element as that could cause a leak. Similarly, the load handler is registered in such a way as to
    // avoid a closure.
    iframe.onload = download.onload;


    form = Ext.DomHelper.append(document.body, {
        action: config.url,
        cn: inputs,
        method: config.method || 'GET',
        tag: 'form',
        target: frameId
    });

    form.submit();

    removeNode(form);

    // Can't remove the iframe immediately or the download won't happen, so wait for 10 minutes
    Ext.defer(removeNode, 1000 * 60 * 10, null, [iframe]);

    function paramsToInputs(params) {

        var inputs = [];
        for (var key in params) {
            var values = [].concat(params[key]);
            Ext.each(values, function(value) {
                inputs.push(createInput(key, value));
            });
        }
        return inputs;

    }

    function createInput(key, value) {
        return {
            name: Ext.htmlEncode(key),
            tag: 'input',
            type: 'hidden',
            value: Ext.htmlEncode(value)
        };
    }
};

// Declared outside the download function to avoid a closure
download.onload = function() {
    // Note we only come into here in the failure case, so you'll need to include your own failure handling
    var response = this.contentDocument.body.innerHTML;
    alert("Errore durante l'esportazione.");

};

// Declared outside the download function to avoid a closure
download.removeNode = function(node) {
    node.onload = null;
    node.parentNode.removeChild(node);
};



Ext.util.base64 = {

    base64s : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",

    encode: function(decStr){
        if (typeof btoa === 'function') {
            return btoa(decStr);            
        }
        var base64s = this.base64s;
        var bits;
        var dual;
        var i = 0;
        var encOut = "";
        while(decStr.length >= i + 3){
            bits = (decStr.charCodeAt(i++) & 0xff) <<16 | (decStr.charCodeAt(i++) & 0xff) <<8 | decStr.charCodeAt(i++) & 0xff;
            encOut += base64s.charAt((bits & 0x00fc0000) >>18) + base64s.charAt((bits & 0x0003f000) >>12) + base64s.charAt((bits & 0x00000fc0) >> 6) + base64s.charAt((bits & 0x0000003f));
        }
        if(decStr.length -i > 0 && decStr.length -i < 3){
            dual = Boolean(decStr.length -i -1);
            bits = ((decStr.charCodeAt(i++) & 0xff) <<16) |    (dual ? (decStr.charCodeAt(i) & 0xff) <<8 : 0);
            encOut += base64s.charAt((bits & 0x00fc0000) >>18) + base64s.charAt((bits & 0x0003f000) >>12) + (dual ? base64s.charAt((bits & 0x00000fc0) >>6) : '=') + '=';
        }
        return(encOut);
    },

    decode: function(encStr){
        if (typeof atob === 'function') {
            return atob(encStr); 
        }
        var base64s = this.base64s;        
        var bits;
        var decOut = "";
        var i = 0;
        for(; i<encStr.length; i += 4){
            bits = (base64s.indexOf(encStr.charAt(i)) & 0xff) <<18 | (base64s.indexOf(encStr.charAt(i +1)) & 0xff) <<12 | (base64s.indexOf(encStr.charAt(i +2)) & 0xff) << 6 | base64s.indexOf(encStr.charAt(i +3)) & 0xff;
            decOut += String.fromCharCode((bits & 0xff0000) >>16, (bits & 0xff00) >>8, bits & 0xff);
        }
        if(encStr.charCodeAt(i -2) == 61){
            return(decOut.substring(0, decOut.length -2));
        }
        else if(encStr.charCodeAt(i -1) == 61){
            return(decOut.substring(0, decOut.length -1));
        }
        else {
            return(decOut);
        }
    }

};  