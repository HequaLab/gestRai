/*
 * File: app/view/frmServizi.js
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

Ext.define('Rai.view.frmServizi', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.frmservizi',

    requires: [
        'Rai.view.frmServiziViewModel',
        'Rai.view.frmServiziViewController',
        'Ext.grid.Panel',
        'Ext.grid.column.Column',
        'Ext.view.Table',
        'Ext.toolbar.Paging',
        'Ext.grid.plugin.CellEditing',
        'Ext.form.field.ComboBox',
        'Ext.form.field.TextArea',
        'Ext.button.Button'
    ],

    controller: 'frmservizi',
    viewModel: {
        type: 'frmservizi'
    },
    id: 'frmservizi',
    title: '',

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
            iconCls: '',
            title: '',
            items: [
                {
                    xtype: 'gridpanel',
                    id: 'serviziGrid',
                    glyph: '',
                    iconCls: '',
                    title: 'Elenco Servizi',
                    store: 'storeServizi',
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            width: 129,
                            dataIndex: 'lotto',
                            text: 'Lotto',
                            flex: 1,
                            editor: {
                                xtype: 'textfield'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            width: 129,
                            dataIndex: 'descrizione',
                            text: 'Descrizione',
                            flex: 1,
                            editor: {
                                xtype: 'textfield'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            width: 129,
                            dataIndex: 'codice',
                            text: 'Voce',
                            flex: 1,
                            editor: {
                                xtype: 'textfield'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'tipologia',
                            text: 'Tipologia',
                            flex: 0.6,
                            editor: {
                                xtype: 'textfield'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'lotto',
                            text: 'Lotto',
                            flex: 0.6,
                            editor: {
                                xtype: 'textfield'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'importo',
                            text: 'Importo',
                            editor: {
                                xtype: 'textfield'
                            }
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'note',
                            text: 'Note',
                            flex: 1.2,
                            editor: {
                                xtype: 'textfield'
                            }
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'storeServizi'
                        }
                    ],
                    plugins: [
                        {
                            ptype: 'cellediting'
                        }
                    ]
                }
            ]
        },
        {
            xtype: 'panel',
            region: 'west',
            padding: 1,
            width: 308,
            bodyPadding: 10,
            title: 'Servizio',
            items: [
                {
                    xtype: 'textfield',
                    id: 'descrizioneTxt',
                    fieldLabel: 'Codice Servizio'
                },
                {
                    xtype: 'textfield',
                    id: 'codiceTxt',
                    fieldLabel: 'Voce *:'
                },
                {
                    xtype: 'combobox',
                    id: 'tipologiaCombo',
                    fieldLabel: 'Tipologia *',
                    displayField: 'ragioneSociale',
                    queryMode: 'local',
                    store: [
                        'Canone',
                        'Modulo',
                        'Richiesta',
                        'Trasporto',
                        'Extra Contratto'
                    ],
                    valueField: 'ragioneSociale'
                },
                {
                    xtype: 'combobox',
                    id: 'lottoCombo',
                    fieldLabel: 'Lotto',
                    displayField: 'ragioneSociale',
                    queryMode: 'local',
                    store: [
                        'Lotto 1',
                        'Lotto 2',
                        'Lotto 3',
                        'Lotto 5'
                    ],
                    valueField: 'ragioneSociale'
                },
                {
                    xtype: 'textfield',
                    id: 'importoTxt',
                    fieldLabel: 'Importo'
                },
                {
                    xtype: 'textareafield',
                    id: 'noteServiziTxt',
                    fieldLabel: 'Note'
                },
                {
                    xtype: 'button',
                    text: 'Salva',
                    listeners: {
                        click: 'onSave'
                    }
                },
                {
                    xtype: 'button',
                    text: 'Elimina',
                    listeners: {
                        click: 'onDelete'
                    }
                },
                {
                    xtype: 'button',
                    text: 'Annulla',
                    listeners: {
                        click: 'onClear'
                    }
                }
            ]
        }
    ]

});