/*
 * File: app/view/frmProfiliManager.js
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

Ext.define('Rai.view.frmProfiliManager', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.frmProfiliManager',

    requires: [
        'Rai.view.frmServiziViewModel5',
        'Rai.view.frmServiziViewController5',
        'Ext.grid.Panel',
        'Ext.grid.column.Column',
        'Ext.form.field.ComboBox',
        'Ext.view.Table',
        'Ext.toolbar.Paging',
        'Ext.grid.plugin.CellEditing'
    ],

    controller: 'frmprofilimanager',
    viewModel: {
        type: 'frmprofilimanager'
    },
    height: 753,
    id: 'frmProfiliManager',
    width: 1099,
    layout: 'border',
    title: 'Area utenti',

    items: [
        {
            xtype: 'panel',
            region: 'center',
            layout: 'fit',
            title: '',
            items: [
                {
                    xtype: 'gridpanel',
                    id: 'utentiGrid1',
                    title: 'Elenco utenti',
                    store: 'storeUtenti',
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'userName',
                            text: 'Utente',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'firstName',
                            text: 'Nome',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'lastName',
                            text: 'Cognome',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'email',
                            text: 'eMail',
                            flex: 1.2
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'roles',
                            text: 'Ruolo',
                            flex: 1.2,
                            editor: {
                                xtype: 'combobox',
                                queryMode: 'local',
                                store: [
                                    'SOperatore',
                                    'Operatore'
                                ]
                            }
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'storeUtenti'
                        }
                    ],
                    plugins: [
                        {
                            ptype: 'cellediting'
                        }
                    ]
                }
            ]
        }
    ],
    listeners: {
        afterrender: 'onFrmUtentiManagerAfterRender'
    }

});