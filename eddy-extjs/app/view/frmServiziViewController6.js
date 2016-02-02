/*
 * File: app/view/frmServiziViewController6.js
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

Ext.define('Rai.view.frmServiziViewController6', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.frmreportmanager',

    onInizioReportSelect: function(field, value, eOpts) {
        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter(
        {
            id:"inizioData",
            property: 'data',
            value:"'" + value.toString("yyyy-M-d") + "'",
            operator: 'gt'
        });

    },

    onFineReportSelect: function(field, value, eOpts) {
        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter(
        {
            id:"fineData",
            property: 'data',
            value:"'" + value.toString("yyyy-M-d") + "'",
            operator: 'lt'
        });

    },

    onCheckboxgroupChange: function(field, newValue, oldValue, eOpts) {
        var bApprovate = Ext.getCmp('checkApprovate').getValue();
        var bLavorazione = Ext.getCmp('checkLavorazione').getValue();
        var bErogate= Ext.getCmp('checkErogate').getValue();

        var filtro = [];
        if (bApprovate)filtro.push('Approvato');
        if (bLavorazione)filtro.push('In lavorazione');
        if (bErogate)filtro.push('Erogato');

        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter(
        {
            property:"stato",
            value:"[" + filtro.toString() + "]",
            operator:"in"
        }
        );

    },

    changeOperatore: function(combo, record, eOpts) {
        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "operatore",
            value: combo.getValue(),
            operator: "streq"
        });
    },

    changeLotto11: function(combo, record, eOpts) {

        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "utenteApprovante",
            value: combo.getValue() ,
            operator: "streq"
        });
    },

    changeLotto: function(combo, record, eOpts) {

        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "lotto",
            value: combo.getValue() ,
            operator: "streq"
        });
    },

    changeTipologia: function(combo, record, eOpts) {

        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "tipologia",
            value: combo.getValue() ,
            operator: "streq"
        });

    },

    changeServizio: function(combo, record, eOpts) {

        Ext.getCmp('filtroVoce').setValue(record);
        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "nome",
            value: combo.getValue() ,
            operator: "streq"
        });

    },

    changeServizio1: function(combo, record, eOpts) {
        Ext.getCmp('filtroServizio').setValue(record);
        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "voce",
            value: combo.getValue() ,
            operator: "streq"
        });

    },

    changeLuogo: function(combo, record, eOpts) {
        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        //store.removeFilter("filtroLuogo");
        store.filter({
            id:"filtroLuogo",
            property: "luogoId",
            value: record.get('luoghiId') ,
            operator: "streq"
        });
    },

    onTextfieldChange: function(field, newValue, oldValue, eOpts) {
        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "uorg",
            value: newValue ,
            operator: "streq"
        });
    },

    onFiltroMatricolaSelect: function(combo, record, eOpts) {
        Ext.getCmp('filtroProduzione').setValue(record);
        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "matricola",
            value: combo.getValue() ,
            operator: "streq"
        });
    },

    onFiltroProduzioneSelect: function(combo, record, eOpts) {
        Ext.getCmp('filtroMatricola').setValue(record);

        var store =  Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "produzione",
            value: combo.getValue() ,
            operator: "streq"
        });
    },

    onFiltroNoteChange: function(field, newValue, oldValue, eOpts) {
        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter(
        {
            id:"filtroNote",
            property:"note",
            value:newValue,
            operatore:"like"
        });
    },

    onPanelAfterRender: function(component, eOpts) {
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


        var storeUser = Ext.StoreManager.lookup('storeUtenti');

        storeUser.filter({
            property: "divisione",
            value: USER.divisione ,
            operator: "streq"
        });

        storeUser.filter({
            property: "superiore",
            value: "%"+USER.userName+"%",
            operator: "like"
        });


    },

    onToolClick: function(tool, e, owner, eOpts) {
        return ;
        var win = Ext.create('Ext.window.Window', {
            height: 634,
            width: 780,
            layout: 'border',
            title: 'Report servizio',
            titleAlign: 'center',
            maximizable: true,
            autoDestroy: true,
            closeAction: 'destroy',
            items: [{
                xtype: 'component',
                html : '<iframe src="'+ "http://www.youtube.com/embed/XGSy3_Czz8k?autoplay=1" + '" width="100%" height="100%"></iframe>',
            }]
        });
        win.show();
    }

});
