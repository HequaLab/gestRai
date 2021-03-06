/*
 * File: app/view/frmServGeneraliViewController.js
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

Ext.define('Rai.view.frmServGeneraliViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.frmservgenerali',

    onFrmServGeneraliAfterRender: function(component, eOpts) {
        this.interval = setInterval(function() {
            var store = Ext.StoreMgr.lookup('storeRichiesteServizi');
            store.reload();
        }, 1000 * 60 * /* minuti */ 5 /* minuti */);




        var store = Ext.StoreManager.lookup('storeRichiesteServizi');
        store.clearFilter(true);
        store.filter({
            property: "divisione",
            value: USER.divisione ,
            operator: "streq"
        });
        store.load();



    },

    onFrmServGeneraliDestroy: function(component, eOpts) {
        clearInterval(this.interval);
    }

});
