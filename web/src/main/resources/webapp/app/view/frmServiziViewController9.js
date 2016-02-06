/*
 * File: app/view/frmServiziViewController9.js
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

Ext.define('Rai.view.frmServiziViewController9', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.frmmatricole',

    onSave: function(button, e, eOpts) {
        var nome = Ext.getCmp('nomeMatricolaTxt').getValue();
        if (nome === undefined || nome == "" ){
            Ext.Msg.show({
                title:'Errore',
                msg: "Il nome non puo' essere vuoto!",
                buttons: Ext.Msg.Ok,
            });
            return;
        }

        var store = Ext.StoreManager.lookup('storeMatricole');
        var emptyApp = new Rai.model.matricole();
        emptyApp.set("descrizione",nome);
        emptyApp.set("divisione",USER.divisione);
        store.add(emptyApp);
        store.on('write', function() { store.load();});
    },

    onDelete: function(button, e, eOpts) {
        var selection = Ext.getCmp('matricolaGrid').getSelectionModel().getSelection();
        var store = Ext.StoreManager.lookup('storeMatricole');
        store.remove(selection);
    },

    onButtonOnClear: function() {
        Ext.getCmp('nomeFilialeTxt').setValue('');
    },

    onFrmMatricoleAfterRender: function(component, eOpts) {

        var store = Ext.StoreManager.lookup('storeMatricole');
        store.filter({
            property: "divisione",
            value: USER.divisione ,
            operator: "streq"
        });
        store.load();
    }

});
