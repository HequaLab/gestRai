/*
 * File: app/view/creaLuogo.js
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

Ext.define('Rai.view.creaLuogo', {
    extend: 'Ext.window.Window',
    alias: 'widget.creaLuogo',

    requires: [
        'Rai.view.creaLuogoViewModel',
        'Ext.form.field.Text',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'crealuogo'
    },
    height: 214,
    width: 437,
    layout: 'absolute',
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABl0lEQVQ4T6VTTU7bUBiccYSKRJG4Ae4SISdmgZJd0xO0vQEcgPycIM4JQsIFuAFwgoadUTcObtVlkhtECiAihAd9z0lEKiqlwptnvfm+mXnzvUe88+P/9Ied4c6md//Veh6zrauk+WmyNoE1f/DufhAaGIHIcPb8sbo2Qbn3q0FlYVwvHhlBpXt7DqL/LgLRS9YmcEcoTEcELtwRgO+z521/SWAFG3jwfzb3k0Wwh53fof0v9irdNALRcrjQjutBtCQwUFA9ywpfrGGlmIjik6C9cGH9pr6cQp7wdEiwK6oBcQhoDHCUu5EPcFee2l5G58rUbXUOnBrkW8Ll7u0lwcQK8v28eO4wnGXbbgqbhem3uFY851I92ziIm3ujci9NSDTik6C/QnCWVpWhc1MPDiqdP74KT4kL8bW6c9NLFdeCuTM3a8W14vGbGDhibhklwIvgaSzhlNAphBbIcR6Bdg0X1TR3kHzDBQxypbO0CiGSVCK5A+AaefL91ziAz5ImJAcLfOUiOSILbd7490N7C1/7Jv7r1b4AcsffioDfOQcAAAAASUVORK5CYII=',
    title: 'Nuovo luogo',
    titleAlign: 'center',

    items: [
        {
            xtype: 'textfield',
            x: 30,
            y: 20,
            id: 'descrId',
            width: 360,
            fieldLabel: 'Descrizione'
        },
        {
            xtype: 'textfield',
            x: 30,
            y: 50,
            id: 'indirizzoId',
            width: 360,
            fieldLabel: 'Indirizzo'
        },
        {
            xtype: 'textfield',
            x: 30,
            y: 80,
            id: 'capId',
            width: 360,
            fieldLabel: 'CAP'
        },
        {
            xtype: 'button',
            handler: function(button, e) {
                var win = this.up('window');
                var descrizione = Ext.getCmp('descrId').getValue(),
                    indirizzo = Ext.getCmp('indirizzoId').getValue(),
                    cap = Ext.getCmp('capId').getValue();


                if (descrizione === undefined || descrizione == "" ){
                    Ext.Msg.show({
                        title:'Errore',
                        msg: "La descrizione non puo' essere vuota!",
                        buttons: Ext.Msg.Ok,
                    });
                    return;
                }

                if (indirizzo === undefined || indirizzo == "" ){
                    Ext.Msg.show({
                        title:'Errore',
                        msg: "La descrizione non puo' essere vuota!",
                        buttons: Ext.Msg.Ok,
                    });
                    return;
                }

                var store = Ext.StoreManager.lookup('storeLuoghi');
                var emptyApp = new Rai.model.luoghi();
                emptyApp.set("descrizione",descrizione);
                emptyApp.set("indirizzo",indirizzo);
                emptyApp.set("cap",cap);
                emptyApp.set("divisione",USER.divisione);
                store.add(emptyApp);
                store.on('write', function() { store.load(); win.close();});



            },
            x: 30,
            y: 120,
            width: 210,
            icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAu0lEQVQ4T+2TyxHCMAxE31YAdEAHQCdQAaQykgqglJRACVDBMvY4GefjAHd8skfaN5JWFgvH9hOwpE0pTR8ADnFJxbw/AAYzsL0GtpLaMDzbgyHa3gMPScGdeMaAINwBlaQ6B9i+AFeglXQoAWrgnIJVEoRnfm8kBdi0glR2DhmvyUA8aaHLtj0HmYiLgJlKZsWLgAwSVrnvedxT70KyaLX0N7LYq7M6AjKLvtTHtGh1BzgCt1/UwEnS/Q3QE10RCutRsgAAAABJRU5ErkJggg==',
            text: 'Salva'
        },
        {
            xtype: 'button',
            handler: function(button, e) {
                this.up('window').close();
            },
            x: 250,
            y: 120,
            width: 140,
            icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAz0lEQVQ4T6WTYRWCQBCEv00gETCBNlAbUMEEQgONYAMj2EAjGMEINFjf8MAHxyEC9/duvp2d3TMWHovp3T0BEjN7j/F7gFr8AAoze04CtMRb4DAJEIhV+H9ARNw4fwFq4zqUh/0Qh+3fgaOZle0LAVRhNxZWfS+xWpOz6gigwARZzYFUY5wBkQM5Kb97EIEUQGN1D2TApuXyYmbnziIFkN4Y3T0FcuAEKI91bBObTLKhRXJ3OboBXQeNvdoJ7bTDgOs3efQz/TkNhZ8uAqjQBy4jVzUc52twAAAAAElFTkSuQmCC',
            text: 'Annulla'
        }
    ]

});