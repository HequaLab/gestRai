/*
 * File: app/view/frmLuoghi.js
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

Ext.define('Rai.view.frmLuoghi', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.frmLuoghi',

    requires: [
        'Rai.view.frmServiziViewModel11',
        'Rai.view.frmServiziViewController11',
        'Ext.grid.Panel',
        'Ext.grid.column.Column',
        'Ext.view.Table',
        'Ext.toolbar.Paging',
        'Ext.button.Button'
    ],

    controller: 'frmluoghi',
    viewModel: {
        type: 'frmluoghi'
    },
    height: 753,
    id: 'frmLuoghi',
    padding: 0,
    width: 1099,
    title: 'Area luoghi',
    titleAlign: 'center',

    layout: {
        type: 'border',
        padding: 1
    },
    items: [
        {
            xtype: 'panel',
            region: 'center',
            padding: 1,
            layout: 'fit',
            title: '',
            items: [
                {
                    xtype: 'gridpanel',
                    id: 'luogoGrid',
                    title: 'Elenco luoghi',
                    store: 'storeLuoghi',
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'descrizione',
                            text: 'Descrizione',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'indirizzo',
                            text: 'Indirizzo',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'cap',
                            text: 'Cap',
                            flex: 1
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'storeLuoghi'
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    handler: function(button, e) {
                                        var panel = Ext.widget('creaLuogo');
                                        panel.show();

                                    },
                                    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAYUlEQVQ4T2NkwAHMJ136wMjAyA+S/v///4eT+XqC2JQy4jLAYtLl/8hyJ/J0saodNYCBgRE5tHEFKC5xUOwwooc2qYZQbgBFXmD4/3E0HTAw0C4MULIzw/+PJ/P0BLClEQCgqEhdko1z8AAAAABJRU5ErkJggg==',
                                    text: 'Nuovo luogo'
                                },
                                {
                                    xtype: 'button',
                                    handler: function(button, e) {

                                        var selection = Ext.getCmp('luogoGrid').getSelectionModel().getSelection();
                                        if (selection === null || selection === undefined)return;
                                        var store = Ext.StoreManager.lookup('storeLuoghi');
                                        store.remove(selection);
                                    },
                                    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAA90lEQVQ4T2NkQAMWEy83MDAy1KOLg/n/GRpP5Os2IMsxYhgw6fL/H395BC8UKn5AljPovy/Awfzl/Yk8XRQ9YA5eW7E6BeEaiAE4bMWlF9k1cAMYGBlQ/IZLM1z8P0MDyDtgA8wmXwpg+s+4HhRIBDWCFDAy1P9j/B94KldvAzxAQN4AmWg+6UoBw////0/m607ExYaphZgFBTBBcIAyMDCAogsnG2rZqAGQFEn9QDSfeGkDyOST+XoBuNhYo9F80qUP//4yO5wu1L6ALzGZ9l81YGb+t+FEnq4CqhcmX3Zg+M+wgIGBQZ5AanzIwMiQcCJX9wBIHQCNDPARwsqYOgAAAABJRU5ErkJggg==',
                                    text: 'Elimina'
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ],
    listeners: {
        afterrender: 'onFrmLuoghiAfterRender'
    }

});