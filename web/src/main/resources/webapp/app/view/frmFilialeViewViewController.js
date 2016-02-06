/*
 * File: app/view/frmFilialeViewViewController.js
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

Ext.define('Rai.view.frmFilialeViewViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.frmoperatore',

    onRichiesteClick: function(button, e, eOpts) {

        Ext.getCmp("mainContainer").removeAll();
        var cmp = Ext.create("Rai.view.frmRichiestaNuovoServizio");
        Ext.getCmp("mainContainer").add(cmp);
        cmp.show();
    },

    onLogoutClick: function(button, e, eOpts) {

        var stores = Ext.data.StoreManager.map;
        for (name in stores) {
            if (stores.hasOwnProperty(name)) {
                store.autoSync = false;
                store.autoLoad = false;
                store = stores[name];
                store.clearFilter(true);
                store.proxy.headers = null;
            }
        }

        Ext.getCmp("pageContainer").removeAll();
        var cmp = Ext.create("Rai.view.frmLogin");
        Ext.getCmp("pageContainer").add(cmp);
        cmp.show();
    },

    onPanelAfterRender: function(component, eOpts) {

        Ext.getCmp("mainContainer").removeAll();
        var cmp = Ext.create("Rai.view.frmRichiestaNuovoServizio");
        Ext.getCmp("mainContainer").add(cmp);
        cmp.show();
        Ext.getCmp('frmOperatore_').title = "Gestione Lavorazioni - Operatore divisione:" + USER.divisione;

    }

});
