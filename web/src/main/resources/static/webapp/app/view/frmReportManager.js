/*
 * File: app/view/frmReportManager.js
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

Ext.define('Rai.view.frmReportManager', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.frmReportManager',

    requires: [
        'Rai.view.frmServiziViewModel6',
        'Rai.view.frmServiziViewController6',
        'Ext.grid.Panel',
        'Ext.grid.column.Date',
        'Ext.view.Table',
        'Ext.toolbar.Paging',
        'Ext.form.Label',
        'Ext.toolbar.Separator',
        'Ext.form.field.Date',
        'Ext.button.Cycle',
        'Ext.menu.Menu',
        'Ext.menu.CheckItem',
        'Ext.form.CheckboxGroup',
        'Ext.form.field.Checkbox',
        'Ext.form.field.ComboBox',
        'Ext.grid.feature.Summary',
        'Ext.grid.filters.Filters',
        'Ext.form.field.TextArea',
        'Ext.panel.Tool'
    ],

    controller: 'frmreportmanager',
    viewModel: {
        type: 'frmreportmanager'
    },
    height: 753,
    padding: 0,
    width: 1099,
    title: 'Rai - Area  analitycs',
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
                    id: 'filialeGrid1',
                    header: false,
                    title: 'Elenco divisioni',
                    store: 'storeRichiesteServizi',
                    columns: [
                        {
                            xtype: 'datecolumn',
                            summaryRenderer: function(val, params, data) {
                                return "";
                            },
                            summaryType: 'count',
                            id: 'inizioData',
                            dataIndex: 'data',
                            text: 'Data inizio',
                            flex: 1,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'datecolumn',
                            summaryRenderer: function(val, params, data) {
                                return "";
                            },
                            summaryType: 'count',
                            dataIndex: 'dataFine',
                            text: 'Data fine',
                            flex: 1,
                            format: 'd-m-Y'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'stato',
                            text: 'Stato',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'operatore',
                            text: 'Op. richiedente',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'utenteApprovante',
                            text: 'Op. approvante',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'lotto',
                            text: 'Lotto',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'tipologia',
                            text: 'Tipologia',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'luogo',
                            text: 'Luogo',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'uorg',
                            text: 'Uorg',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'produzione',
                            text: 'Produzione',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'matricola',
                            text: 'Matricola',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return value;
                            },
                            dataIndex: 'ora',
                            text: 'Ora',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return value;
                            },
                            summaryRenderer: function(val, params, data) {
                                return "";
                            },
                            summaryType: 'sum',
                            dataIndex: 'ore',
                            text: 'Ore/Qt.a',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            summaryRenderer: function(val, params, data) {
                                return "";
                            },
                            summaryType: 'sum',
                            dataIndex: 'nome',
                            text: 'Servizio',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            summaryRenderer: function(val, params, data) {
                                return "";
                            },
                            summaryType: 'sum',
                            dataIndex: 'voce',
                            text: 'Voce',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return '&#8364;' + ' '  + value;
                            },
                            summaryRenderer: function(val, params, data) {
                                return "";
                            },
                            summaryType: 'sum',
                            dataIndex: 'importo',
                            text: 'Costo unitario',
                            flex: 1
                        },
                        {
                            xtype: 'gridcolumn',
                            summaryRenderer: function(val, params, data) {

                                return '<div style="color:Blue;"><b>Tot. &#8364 ' + val + "</b></div>";
                            },
                            renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return '&#8364;' + ' '  +  Ext.util.Format.number(value, '0.00');
                            },
                            summaryType: 'sum',
                            baseCls: 'costoTotaleTitle',
                            dataIndex: 'costoTotale',
                            tdCls: 'costoTotale',
                            text: 'Costo totale',
                            flex: 1
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'storeRichiesteServizi'
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            cls: 'toolbarFilter',
                            height: 39,
                            items: [
                                {
                                    xtype: 'label',
                                    flex: 0.8,
                                    cls: 'x-field',
                                    componentCls: 'x-field',
                                    text: 'Seleziona range temporale'
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'datefield',
                                    flex: 1,
                                    tabIndex: 1,
                                    id: 'inizioReport',
                                    width: 230,
                                    fieldLabel: 'Inizio',
                                    labelWidth: 60,
                                    format: 'd/m/Y',
                                    listeners: {
                                        select: 'onInizioReportSelect'
                                    }
                                },
                                {
                                    xtype: 'datefield',
                                    flex: 1,
                                    tabIndex: 1,
                                    id: 'fineReport',
                                    width: 235,
                                    fieldLabel: 'Fine',
                                    labelWidth: 60,
                                    format: 'd/m/Y',
                                    listeners: {
                                        select: 'onFineReportSelect'
                                    }
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'cycle',
                                    flex: 1,
                                    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABJ0lEQVQ4T6WT0W3CMBCG/9/uS6UgdQOyAd2AjMBTgTdG6AYtG3SEvEH6xgbpBrBB2ACJVH1pfJVthSaRqZF6Lz77zt+d785ER1RxfrVbMx+5dShqWz8DSM0isasTovicKWBCyhEiK3/KXITjLiBkN8CBuqjFO8pHPyKn/X3YfgEIsAdk51mckZh0AQI5QbgG5YFABvgAnQwAD7HvwmOoBiKoQJy6dgcQyDp0IXZG8MUBmnnCq86brxQaYzQ4Op9WX95X9m4UYFtrI7VZtrpt9U0AbM6ZUsiMQWkTuOjLUdkHOEc1Nca4dkb1IeBaqr4rtzzBFgvfKXBX+YJG9GER/52Bfq9X7i+QuZ/Gv/XmKcl/i0idxYYmZNfSlFTb+o2U4OjGoCLc/wBZGuZAbFHcHQAAAABJRU5ErkJggg==',
                                    text: 'Seleziona',
                                    menu: {
                                        xtype: 'menu',
                                        minWidth: 200,
                                        width: 120,
                                        collapsed: false,
                                        items: [
                                            {
                                                xtype: 'menucheckitem',
                                                handler: function(item, e) {
                                                    var dataInizio = new Date().moveToFirstDayOfMonth ( ),
                                                        dataFine = new Date().moveToLastDayOfMonth ( );
                                                    Ext.getCmp('inizioReport').setValue(dataInizio);
                                                    Ext.getCmp('fineReport').setValue(dataFine);


                                                    var store = Ext.StoreManager.lookup('storeRichiesteServizi');
                                                    store.filter(
                                                    {
                                                        property: 'data',
                                                        value: "'" + dataInizio.toString("yyyy-M-d") + "'",
                                                        operator: 'gt'
                                                    });
                                                    store.filter(
                                                    {
                                                        property: 'data',
                                                        value: "'" + dataFine.toString("yyyy-M-d") + "'",
                                                        operator: 'lt'
                                                    });
                                                },
                                                text: 'Questo mese',
                                                checked: true
                                            },
                                            {
                                                xtype: 'menucheckitem',
                                                handler: function(item, e) {
                                                    var store = Ext.StoreManager.lookup('storeRichiesteServizi');
                                                    var dataInizio = new Date().addMonths(-1).moveToFirstDayOfMonth ( ),
                                                        dataFine = new Date().addMonths(-1).moveToLastDayOfMonth ( );
                                                    Ext.getCmp('inizioReport').setValue(dataInizio);
                                                    Ext.getCmp('fineReport').setValue(dataFine);

                                                    store.filter(
                                                    {
                                                        property: 'data',
                                                        value: "'" + dataInizio.toString("yyyy-M-d") + "'",
                                                        operator: 'gt'
                                                    });
                                                    store.filter(
                                                    {
                                                        property: 'data',
                                                        value: "'" + dataFine.toString("yyyy-M-d") + "'",
                                                        operator: 'lt'
                                                    });
                                                },
                                                text: 'Ultimo mese'
                                            }
                                        ]
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            cls: 'toolbarFilter',
                            items: [
                                {
                                    xtype: 'checkboxgroup',
                                    flex: 1.5,
                                    width: 400,
                                    fieldLabel: '',
                                    labelWidth: 60,
                                    items: [
                                        {
                                            xtype: 'checkboxfield',
                                            id: 'checkApprovate',
                                            boxLabel: 'Approvate',
                                            checked: true
                                        },
                                        {
                                            xtype: 'checkboxfield',
                                            id: 'checkLavorazione',
                                            boxLabel: 'In lavorazione',
                                            checked: true
                                        },
                                        {
                                            xtype: 'checkboxfield',
                                            id: 'checkErogate',
                                            boxLabel: 'Erogate',
                                            checked: true
                                        }
                                    ],
                                    listeners: {
                                        change: 'onCheckboxgroupChange'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    tabIndex: 3,
                                    id: 'filtroOperatore',
                                    fieldLabel: 'Operatore',
                                    labelWidth: 70,
                                    displayField: 'nameComplete',
                                    queryMode: 'local',
                                    store: 'storeUtenti',
                                    valueField: 'nameComplete',
                                    listeners: {
                                        select: 'changeOperatore'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    tabIndex: 3,
                                    id: 'filtroOperatoreApprovante',
                                    fieldLabel: 'Operatore approvante',
                                    labelWidth: 70,
                                    displayField: 'nameComplete',
                                    queryMode: 'local',
                                    store: 'storeUtenti',
                                    valueField: 'nameComplete',
                                    listeners: {
                                        select: 'changeLotto11'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    flex: 1,
                                    tabIndex: 3,
                                    id: 'filtroLotto',
                                    fieldLabel: 'Lotto',
                                    labelWidth: 40,
                                    queryMode: 'local',
                                    store: [
                                        'Lotto 1',
                                        'Lotto 2',
                                        'Lotto 3',
                                        'Lotto 5'
                                    ],
                                    listeners: {
                                        select: 'changeLotto'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    flex: 1,
                                    tabIndex: 4,
                                    id: 'filtroTipologia',
                                    fieldLabel: 'Tipologia',
                                    labelWidth: 60,
                                    queryMode: 'local',
                                    store: [
                                        'Canone',
                                        'Modulo',
                                        'Richiesta',
                                        'Trasporto'
                                    ],
                                    listeners: {
                                        select: 'changeTipologia'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            cls: 'toolbarFilter',
                            items: [
                                {
                                    xtype: 'combobox',
                                    flex: 1,
                                    tabIndex: 5,
                                    id: 'filtroServizio',
                                    fieldLabel: 'Servizio',
                                    labelWidth: 50,
                                    displayField: 'descrizione',
                                    queryMode: 'local',
                                    store: 'storeServizi',
                                    valueField: 'descrizione',
                                    listeners: {
                                        select: 'changeServizio'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    flex: 1,
                                    tabIndex: 6,
                                    id: 'filtroVoce',
                                    fieldLabel: 'Voce',
                                    labelWidth: 50,
                                    displayField: 'codice',
                                    queryMode: 'local',
                                    store: 'storeServizi',
                                    valueField: 'codice',
                                    listeners: {
                                        select: 'changeServizio1'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    flex: 1,
                                    tabIndex: 8,
                                    fieldLabel: 'Luogo',
                                    labelWidth: 40,
                                    displayField: 'descrizione',
                                    store: 'storeLuoghi',
                                    valueField: 'descrizione',
                                    listeners: {
                                        select: 'changeLuogo'
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    flex: 1,
                                    tabIndex: 9,
                                    fieldLabel: 'Uorg',
                                    labelWidth: 40,
                                    listeners: {
                                        change: 'onTextfieldChange'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    tabIndex: 7,
                                    id: 'filtroMatricola',
                                    fieldLabel: 'Matricola',
                                    labelWidth: 65,
                                    displayField: 'matricola',
                                    store: 'storeProduzioni',
                                    valueField: 'matricola',
                                    listeners: {
                                        select: 'onFiltroMatricolaSelect'
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    tabIndex: 7,
                                    id: 'filtroProduzione',
                                    fieldLabel: 'Produzione',
                                    labelWidth: 70,
                                    displayField: 'descrizione',
                                    store: 'storeProduzioni',
                                    valueField: 'descrizione',
                                    listeners: {
                                        select: 'onFiltroProduzioneSelect'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            cls: 'toolbarFilter',
                            items: [
                                {
                                    xtype: 'textareafield',
                                    height: 30,
                                    id: 'filtroNote',
                                    width: 400,
                                    fieldLabel: 'Note',
                                    labelWidth: 50,
                                    listeners: {
                                        change: 'onFiltroNoteChange'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    handler: function(button, e) {



                                        var dataInizio = new Date().moveToFirstDayOfMonth ( ),
                                            dataFine = new Date().moveToLastDayOfMonth ( );
                                        Ext.getCmp('inizioReport').setValue(dataInizio);
                                        Ext.getCmp('fineReport').setValue(dataFine);

                                        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
                                        store.clearFilter();


                                        store.filter(
                                        {
                                            property: 'stato',
                                            value: '[In lavorazione,Approvato,Erogato]',
                                            operator: 'in'
                                        });


                                        store.filter(
                                        {
                                            id:"inizioData",
                                            property: 'data',
                                            value: "'" + dataInizio.toString("yyyy-M-d") + "'",
                                            operator: 'gt'
                                        });
                                        store.filter(
                                        {
                                            id:"fineData",
                                            property: 'data',
                                            value:"'" + dataFine.toString("yyyy-M-d") + "'",
                                            operator: 'lt'
                                        });



                                        Ext.getCmp('filtroLotto').setValue(null);
                                        Ext.getCmp('filtroTipologia').setValue(null);
                                        Ext.getCmp('filtroServizio').setValue(null);
                                        Ext.getCmp('filtroVoce').setValue(null);
                                        Ext.getCmp('filtroProduzione').setValue(null);
                                        Ext.getCmp('filtroMatricola').setValue(null);
                                        Ext.getCmp('filtroOperatore').setValue(null);
                                        Ext.getCmp('filtroOperatoreApprovante').setValue(null);
                                        Ext.getCmp('filtroNote').setValue(null);
                                        Ext.getCmp('filtroUorg').setValue(null);
                                        Ext.getCmp('luogo').setValue(null);
                                        Ext.getCmp('checkApprovate').setValue(true);
                                        Ext.getCmp('checkLavorazione').setValue(true);
                                        Ext.getCmp('checkErogate').setValue(true);

                                    },
                                    flex: 1,
                                    width: 100,
                                    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABcklEQVQ4T5WTQVKDQBBF+5OyXFouDFQZKDyB8QTqCRJPIAnuk5zAeAJxLyQ3EE+gN1BvgJAFxIVry4K2hjiEmJDCWU1N97z53f0HVLFioz8kRmf3O+3tx9OgKg9/Ax+tq7MMPCBQV8aYeKSFnrMJsgJIdHtKoEsmeifmAMCp2DcyWAez+2cBZ2SdZuSNJKwALC8vXkt0u0vgthp6Y5k81/sWAxPO6E6buUNxngNy2Qo/bZMqIaI3INwqGc6FqhyQ6LbPoLYWumZVs8rnsWEHYHpVI7e7ABg2E/FNWe42kFShhi4g5RPThRq5fh0F8g7S9ATzQ6vNjcaLrKkOYNFgesgBsoQ6DZTwxOiPiXCdl7BsIh9roXdUR8FaE/8zRvn6yhh/VeQuBHOvGXnTFbkMMTI/btkOFBqsGakwSct2SOFAOFGoSpVsAoJJzG8M2hP78uXCiZvqlrMuYkyPCsMR7ivnr/3GcvBTs8yvHUWU5lf9xh9JINE7IsEgWQAAAABJRU5ErkJggg==',
                                    text: 'Pulisci filtri'
                                },
                                {
                                    xtype: 'button',
                                    handler: function(button, e) {
                                        var filterObj = [];

                                        var storeFilters = Ext.StoreManager.lookup('storeRichiesteServizi').getFilters();
                                        for (var i = 0; i < storeFilters.items.length; i++){
                                            var temp = {
                                                property:storeFilters.items[i]._property,
                                                value:storeFilters.items[i]._value,
                                                operator:storeFilters.items[i]._operator
                                            };
                                            filterObj.push(temp);
                                        }

                                        var url =  '/richiestaNuovoServizio/reportPdf/' +  ( Ext.getCmp('inizioReport').getValue().getTime()) + '/' + (Ext.getCmp('fineReport').getValue().getTime());
                                        download({
                                            url: '/richiestaNuovoServizio/reportPdf/' +  ( Ext.getCmp('inizioReport').getValue().getTime()) + '/' + (Ext.getCmp('fineReport').getValue().getTime()),
                                            method: 'GET',
                                            params : {filter:JSON.stringify(filterObj), auth:ACCESS_TOKEN},
                                        },Ext.id());




                                    },
                                    flex: 1,
                                    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABlklEQVQ4T32SMXKbQBiF31vJaQQz3MAU7lKEBrUm0gXQDXQEH0G+gXID5QTGfYxJK7ngCDqCPCaNNezLLBYO0YC2Yf6f3W/fvv8Rp1XN442EsK3PvwTDiX2PWJSH7j+2xdtsuvLz7WoIUM2nAlRO6uP3LqQXUM3itYwy/9dL8U+hAwACMv9pu2j7wwDw0FX0oeC0pB9evrtzVT8giSMZrD17TFu5leuNFTSHLNPLgFm8hEUJg+XEHlfnxnX96lXQblASBX9GVw+0WhvwUAuvXrErLwJOh54lPhL2IIOSlqHIlLKFq2FN0vrzn4Kxfd/U5sqZU3r5btMdaQM246WXv6wHFdAqk2F6KQ8O2gtwsx8JD0NB6vZrYtEzhbggsZe4BxFBbmT8CBJ1B6F0NanQRd7Pd8lZDuICRiuImVcfw8p8aYIC4psBflohApSQvJX0exAgqxsD81Xg6+ftVOqcdx5hhFsJi0EAZZ6bzAv3MLYwMoGgtHnaRQXzaUboGmDU3CzsQe0hhiBCVwsKSAYCHv2nbeq2/QUkWxEgOg1QagAAAABJRU5ErkJggg==',
                                    text: 'Esporta PDF'
                                },
                                {
                                    xtype: 'button',
                                    handler: function(button, e) {
                                        var filterObj = [];

                                        var storeFilters = Ext.StoreManager.lookup('storeRichiesteServizi').getFilters();
                                        for (var i = 0; i < storeFilters.items.length; i++){
                                            var temp = {
                                                property:storeFilters.items[i]._property,
                                                value:storeFilters.items[i]._value,
                                                operator:storeFilters.items[i]._operator
                                            };
                                            filterObj.push(temp);
                                        }

                                        var url =  '/richiestaNuovoServizio/reportExcel/' +  ( Ext.getCmp('inizioReport').getValue().getTime()) + '/' + (Ext.getCmp('fineReport').getValue().getTime());
                                        download({
                                            url: '/richiestaNuovoServizio/reportExcel/' +  ( Ext.getCmp('inizioReport').getValue().getTime()) + '/' + (Ext.getCmp('fineReport').getValue().getTime()),
                                            method: 'GET',
                                            params : {filter:JSON.stringify(filterObj), auth:ACCESS_TOKEN},
                                        },Ext.id());



                                    },
                                    flex: 1,
                                    alignTarget: '',
                                    defaultAlign: '',
                                    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABdklEQVQ4T41TQU7CUBScqY2Q2EZu4GelO+oNygFM3BpNxIULYSMnEE8AG2DrEbyBegJ7A783IJRERewzD0Gxrdi/mt+8TN/MnyFSx3SlgtKkBiKEIAAkAHlmL/z79KzeaQbjQ5A1CEJADEGTHvxInAPHSeIMAd1nVgex5DGvfhMkbcLp5m5QhCCBnBM8AVBZkIwUE6gV24CoqwdmGIdKsMQU3H0TCFEHXEuZPYngGlOvh1L8qJ4UkiCCyLb8fdOPe5h6HWxOLklc6R/nJm4kEwiDuQRKpJiQ7i8JArZt0+uZ4YvRTZamCXEEYBeJfL2QQ6uYZCNDgLetG5RfK5T3COC2zs8JEuyBCwKhVUzw9McD4ME2/dD041tMvUZGguYgLYHSWzHRrQIzo86qibbld6r9sQW586+JIjICGS2TmLkXecZ1aVwE6RiyCBIxUkwiKBakdVGel0nNIUKKGNWcWybNQea4lrl1Lk8CJNpOaFgCcdj4q86f2p7XmbzMMbAAAAAASUVORK5CYII=',
                                    text: 'Esporta EXCEL'
                                }
                            ]
                        }
                    ],
                    features: [
                        {
                            ftype: 'summary',
                            dock: 'bottom'
                        }
                    ],
                    plugins: [
                        {
                            ptype: 'gridfilters'
                        }
                    ]
                }
            ]
        }
    ],
    listeners: {
        afterrender: 'onPanelAfterRender'
    },
    tools: [
        {
            xtype: 'tool',
            type: 'help',
            listeners: {
                click: 'onToolClick'
            }
        }
    ]

});