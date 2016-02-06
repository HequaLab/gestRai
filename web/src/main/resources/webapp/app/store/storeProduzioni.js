/*
 * File: app/store/storeProduzioni.js
 *
 * This file was generated by Sencha Architect version 3.2.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 5.1.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 5.1.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Rai.store.storeProduzioni', {
    extend: 'Ext.data.Store',

    requires: [
        'Rai.model.produzioni',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json',
        'Ext.data.writer.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            pageSize: 100,
            remoteFilter: true,
            storeId: 'storeProduzioni',
            autoLoad: false,
            model: 'Rai.model.produzioni',
            proxy: {
                type: 'rest',
                idParam: 'produzioniId',
                url: '/produzioni',
                reader: {
                    type: 'json',
                    rootProperty: 'items'
                },
                writer: {
                    type: 'json',
                    writeRecordId: false
                },
                listeners: {
                    exception: {
                        fn: me.onRestException,
                        scope: me
                    }
                }
            }
        }, cfg)]);
    },

    onRestException: function(proxy, request, operation, eOpts) {
        if (operation.error.status === 401){
            var stores = Ext.data.StoreManager.map;
            for (name in stores) {
                if (stores.hasOwnProperty(name)) {
                    store.autoSync = false;
                    store.autoLoad = false;
                    store = stores[name];
                    store.clearFilter(true);
                    store.proxy.headers = null;
                }
            }

            Ext.MessageBox.show({

                title:"Logout",
                msg:"Sei stato inattivo per troppo tempo, verrai disconnesso,",
                buttons: Ext.Msg.OK,
                fn: function(){
                    Ext.getCmp("pageContainer").removeAll();
                    var cmp = Ext.create("Rai.view.frmLogin");
                    Ext.getCmp("pageContainer").add(cmp);
                    cmp.show();
                    // window.location.reload();
                }


            });



        }
    }

});