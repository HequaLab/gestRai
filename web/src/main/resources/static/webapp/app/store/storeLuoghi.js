/*
 * File: app/store/storeLuoghi.js
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

Ext.define('Rai.store.storeLuoghi', {
    extend: 'Ext.data.Store',

    requires: [
        'Rai.model.luoghi',
        'Ext.data.proxy.Rest',
        'Ext.data.reader.Json',
        'Ext.data.writer.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            remoteFilter: true,
            storeId: 'storeLuoghi',
            autoLoad: false,
            model: 'Rai.model.luoghi',
            proxy: {
                type: 'rest',
                idParam: 'luoghiId',
                url: '/luoghi',
                reader: {
                    type: 'json',
                    rootProperty: 'items'
                },
                writer: {
                    type: 'json',
                    writeRecordId: false
                }
            }
        }, cfg)]);
    }
});