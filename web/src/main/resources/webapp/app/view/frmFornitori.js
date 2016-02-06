/*
 * File: app/view/frmFornitori.js
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

Ext.define('Rai.view.frmFornitori', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.frmfornitori',

    requires: [
        'Rai.view.frmServiziViewModel1',
        'Rai.view.frmServiziViewController1',
        'Ext.grid.Panel',
        'Ext.grid.column.Column',
        'Ext.view.Table',
        'Ext.toolbar.Paging',
        'Ext.form.field.TextArea',
        'Ext.button.Button'
    ],

    controller: 'frmfornitori',
    viewModel: {
        type: 'frmfornitori'
    },
    height: 753,
    id: 'frmfornitori',
    padding: 1,
    width: 1099,
    layout: 'border',
    title: 'Area fornitori',

    items: [
        {
            xtype: 'panel',
            region: 'center',
            layout: 'border',
            title: '',
            items: [
                {
                    xtype: 'gridpanel',
                    region: 'center',
                    id: 'fornitoriGrid',
                    padding: 1,
                    title: 'Elenco Fornitori',
                    store: 'storeFornitori',
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            width: 575,
                            dataIndex: 'ragioneSociale',
                            text: 'Ragione sociale',
                            flex: 2
                        },
                        {
                            xtype: 'gridcolumn',
                            width: 203,
                            dataIndex: 'note',
                            text: 'Note',
                            flex: 1
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'storeFornitori'
                        }
                    ]
                }
            ]
        },
        {
            xtype: 'panel',
            region: 'west',
            padding: 1,
            width: 310,
            bodyPadding: 10,
            title: '',
            items: [
                {
                    xtype: 'textfield',
                    id: 'ragioneSocialeTxt',
                    fieldLabel: 'Ragione Sociale'
                },
                {
                    xtype: 'textareafield',
                    id: 'noteTxt',
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
                        click: 'onButtonClick'
                    }
                }
            ]
        }
    ]

});