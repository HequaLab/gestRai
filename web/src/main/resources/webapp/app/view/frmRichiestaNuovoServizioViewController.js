/*
 * File: app/view/frmRichiestaNuovoServizioViewController.js
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

Ext.define('Rai.view.frmRichiestaNuovoServizioViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.frmrichiestanuovoservizio',

    onShowWin: function(button, e, eOpts) {
        var panel = Ext.widget('winnuovatipologiaservizio');
        panel.show();
        button.disable();
    },

    onLottoChange: function(field, newValue, oldValue, eOpts) {
        var lotto = Ext.getCmp('lotto').getValue(),
            tipologia = Ext.getCmp('comboTipologia').getValue(),
            store = Ext.StoreManager.lookup('storeServizi');
        store.clearFilter();
        store.filter('lotto',lotto);
        if (tipologia !== undefined)store.filter('tipologia',tipologia);

    },

    onServizioCombo1Select: function(field, newValue, oldValue, eOpts) {

        var tipologia = Ext.getCmp('comboTipologia').getValue();
        var store = Ext.StoreManager.lookup('storeServizi');
        store.clearFilter();
        store.filter('tipologia',tipologia);
        store.filter('lotto',Ext.getCmp('lotto').getValue());

        Ext.getCmp('oreRichiesta').labelEl.update('Ore');
        Ext.getCmp('oreRichiesta').enable();

        // Disabilitare numero ore per servizi canone e modulo
        if (tipologia == 'Canone'){
            Ext.getCmp('oreRichiesta').disable();
        }else if (tipologia == "Modulo"){
            Ext.getCmp('oreRichiesta').labelEl.update('Q.ta');
        }

    },

    onMatricolaSelect: function(combo, record, eOpts) {
        Ext.getCmp('matricola').setValue(record);
    },

    onMatricolaSelect1: function(combo, record, eOpts) {
        Ext.getCmp('produzione').setValue(record);
    },

    onServizioComboSelect: function(combo, record, eOpts) {
        Ext.getCmp('codiciCombo').setValue(record);
    },

    onDataRichiestaSelect: function(field, value, eOpts) {
        Ext.getCmp('dataFine').setValue(value);
    },

    onRichiediServizio: function(button, e, eOpts) {
        Ext.Msg.show({
            title: 'Richiesta servizio',
            msg: 'Stai per richiedere un servizio, vuoi continuae?',
            buttons: Ext.Msg.YESNO,
            buttonText: {
                no: 'No',
                yes: 'Si'
            },
            fn: function(btn) {
                if (btn == 'yes') {
                    var servizio = Ext.getCmp('servizioCombo').getValue(),
                        uorg = Ext.getCmp('uorg').getValue(),
                        data = Ext.getCmp('dataRichiesta').getValue(),
                        ora = Ext.getCmp('oraRichiesta').getRawValue(),
                        ore = Ext.getCmp('oreRichiesta').getValue(),
                        dataFine = Ext.getCmp('dataFine').getValue(),
                        note = Ext.getCmp('noteRichiesta').getValue(),
                        filiale = USER.divisione,
                        stato = "Nessuno",
                        tipologia = Ext.getCmp('comboTipologia').getValue(),
                        lotto = Ext.getCmp('lotto').getValue(),
                        matricola = Ext.getCmp('matricola').getRawValue(),
                        produzione = Ext.getCmp('produzione').getRawValue(),
                        luogoId = Ext.getCmp('luogo').getValue(),
                        index = (luogoId !== undefined ? Ext.StoreMgr.lookup("storeLuoghi").findExact('luoghiId', luogoId) : undefined),
                        rec = index !== undefined ? Ext.StoreMgr.lookup("storeLuoghi").getAt(index) : undfined,
                        luogo = rec !== null ? rec.get('descrizione') + " ( " + rec.get('indirizzo') + " - " + rec.get('cap') + " )" : "Nessuno",
                        importo = Ext.StoreManager.lookup("storeServizi").findRecord('serviziId', servizio).get('importo'),
                        nomeServizio = Ext.StoreManager.lookup("storeServizi").findRecord('serviziId', servizio).get('descrizione'),
                        operatore = USER.firstName + " " + USER.lastName,
                        voce = Ext.getCmp('codiciCombo').getRawValue(),
                        idProduzione = Ext.getCmp('produzione').getValue(),
                        idServizio = Ext.getCmp('servizioCombo').getValue();
                    var costoTotale = 0;
                    if (tipologia === 'Canone') costoTotale = importo;
                    else if (tipologia === 'Modulo') costoTotale = importo * ore;
                    else if (tipologia === 'Richiesta') costoTotale = importo * ore;
                    else if (tipologia === 'Trasporto') costoTotale = importo * ore;
                    if (servizio === undefined || servizio === "") {
                        Ext.Msg.show({
                            title: 'Errore',
                            msg: "Non hai selezionato il servizio",
                            buttons: Ext.Msg.Ok,
                        });
                        return;
                    }
                    if ((data === undefined || data === null)) {
                        Ext.Msg.show({
                            title: 'Errore',
                            msg: "Non hai selezionato una data!",
                            buttons: Ext.Msg.Ok,
                        });
                        return;
                    }


                    var store = Ext.StoreManager.lookup('storeRichiesteServizi');
                    var emptyApp = new Rai.model.richiestaServizio();
                    emptyApp.set('nome', nomeServizio);
                    emptyApp.set('importo', importo);
                    emptyApp.set('divisione', filiale);
                    emptyApp.set('ora', ora);
                    emptyApp.set('data', data);
                    emptyApp.set('uorg', uorg);
                    emptyApp.set('ore', ore);
                    emptyApp.set('note', note);
                    emptyApp.set('stato', stato);
                    emptyApp.set('dataFine', dataFine);
                    emptyApp.set('tipologia', tipologia);
                    emptyApp.set('lotto', lotto);
                    emptyApp.set('matricola', matricola);
                    emptyApp.set('produzione', produzione);
                    emptyApp.set('luogo', luogo);
                    emptyApp.set('operatore', operatore);
                    emptyApp.set('voce', voce);
                    emptyApp.set('luogoId', luogoId);
                    emptyApp.set('idProduzione',idProduzione);
                    emptyApp.set('idServizio',idServizio);

                    Ext.Ajax.request({
                        url: '/richiestaNuovoServizio/controlloOrdine',
                        headers: {
                            'Authorization': 'Bearer ' + ACCESS_TOKEN,
                            'Content-Type': 'application/json'
                        },
                        method: 'POST',
                        jsonData: {
                            nome: nomeServizio,
                            divisione: filiale,
                            ora: ora,
                            data: data.getTime() / 1000,
                            uorg: uorg,
                            ore: ore,
                            note: note,
                            dataFine: dataFine.getTime() / 1000,
                            tipologia: tipologia,
                            lotto: lotto,
                            matricola: matricola,
                            produzione: produzione,
                            luogo: luogo
                        },
                        success: function(response, options) {

                            if (response.responseText === "YES") {
                                Ext.Msg.show({
                                    title: 'Richiesta servizio',
                                    msg: "E' gia stato richiesto un servizio simile da un altro operatore, sei sicuro di voler continuare?",
                                    buttons: Ext.Msg.YESNO,
                                    buttonText: {
                                        no: 'No',
                                        yes: 'Si'
                                    },
                                    fn: function(btn) {
                                        if (btn == 'no') {
                                            return;
                                        }else {
                                            Ext.MessageBox.wait('Creazione richiesta in corso..',"Attedi",{
                                                interval: 1000, //bar will move fast!
                                                duration: 50000, // 5 secondi?
                                                increment: 10,
                                                text: '',
                                                scope: this,
                                            });
                                            store.add(emptyApp);

                                            var self = this;
                                            store.on('write', function() {
                                                store.load();
                                                Ext.MessageBox.updateProgress(1);
                                                Ext.MessageBox.hide();
                                            });

                                        }
                                    }
                                });
                            }else {
                                Ext.MessageBox.wait('Creazione richiesta in corso..',"Attedi",{
                                    interval: 1000, //bar will move fast!
                                    duration: 50000, // 5 secondi?
                                    increment: 10,
                                    text: '',
                                    scope: this,
                                });
                                store.add(emptyApp);
                                var self = this;
                                store.on('write', function() {
                                    store.load();
                                    Ext.MessageBox.updateProgress(1);
                                    Ext.MessageBox.hide();
                                });

                            }


                            //  Ext.getCmp('comboTipologia').setValue('Canone');
                        },
                        failure: function(response, options) {
                            Ext.Msg.show({
                                title: 'Errore server durante richiesta servizio',
                                msg: "Risposta: " + response,
                                buttons: Ext.Msg.Ok,
                            });
                        }
                    });
                    Ext.getCmp('oreRichiesta').disable();
                    Ext.getCmp('oreRichiesta').labelEl.update('Q.ta');
                    Ext.getCmp('servizioCombo').setValue(null);
                    Ext.getCmp('uorg').setValue(null);
                    Ext.getCmp('dataRichiesta').setValue(null);
                    Ext.getCmp('oraRichiesta').setValue(null);
                    Ext.getCmp('oreRichiesta').setValue(null);
                    Ext.getCmp('dataFine').setValue(null);
                    Ext.getCmp('noteRichiesta').setValue(null);
                    Ext.getCmp('comboTipologia').setValue(null);
                    Ext.getCmp('lotto').setValue(null);
                    Ext.getCmp('dataFine').setValue(null);
                    Ext.getCmp('codiciCombo').setValue(null);
                    Ext.getCmp('matricola').setValue(null);
                    Ext.getCmp('lotto').setValue(null);
                    Ext.getCmp('luogo').setValue(null);
                    Ext.getCmp('produzione').setValue(null);
                }
            },
            animEl: 'elId'
        });
    },

    onClear: function(button, e, eOpts) {
        Ext.getCmp('servizioCombo').setValue(null);
        Ext.getCmp('dataRichiesta').setValue(null);
        Ext.getCmp('oraRichiesta').setValue(null);
        Ext.getCmp('oreRichiesta').setValue(null);
        Ext.getCmp('dataFine').setValue(null);
        Ext.getCmp('noteRichiesta').setValue(null);
        Ext.getCmp('comboTipologia').setValue(null);
        Ext.getCmp('lotto').setValue(null);
        Ext.getCmp('dataFine').setValue(null);
        Ext.getCmp('codiciCombo').setValue(null);
        Ext.getCmp('matricola').setValue(null);
        Ext.getCmp('lotto').setValue(null);
        Ext.getCmp('luogo').setValue(null);
        Ext.getCmp('produzione').setValue(null);
        Ext.getCmp('uorg').setValue(null);
    },

    onFrmrichiestanuovoservizioAfterRender: function(component, eOpts) {

        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
        store.filter({
            property: "divisione",
            value: USER.divisione ,
            operator: "streq"
        });

        store.sort({
            property: "timeStamp",
            direction: "ASC"
        });

        //store.load();

        store = Ext.StoreManager.lookup('storeProduzioni');
        store.filter({
            property: "divisione",
            value: USER.divisione ,
            operator: "streq"
        });

        store = Ext.StoreManager.lookup('storeLuoghi');
        store.filter({
            property: "divisione",
            value: USER.divisione ,
            operator: "streq"
        });

        store = Ext.StoreManager.lookup('storeServizi');
        store.load();

        if (USER.roles.toLowerCase().indexOf("soperatore") >= 0){
            Ext.getCmp('approvaSubmit').setVisible(true);
            Ext.getCmp('rifiutaSubmit').setVisible(true);

        }
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
            closeAction: 'hide',
            items: [{
                xtype: 'component',
                html : '<iframe src="'+ "http://www.youtube.com/embed/XGSy3_Czz8k?autoplay=1" + '" width="100%" height="100%"></iframe>',
            }]
        });
        win.show();
    }

});