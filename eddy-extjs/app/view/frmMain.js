/*
 * File: app/view/frmMain.js
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

Ext.define('Rai.view.frmMain', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.frmMain',

    requires: [
        'Rai.view.frmMainViewModel',
        'Rai.view.frmMainViewController',
        'Ext.container.Container'
    ],

    controller: 'mainForm',
    viewModel: {
        type: 'frmmain'
    },
    layout: 'border',

    items: [
        {
            xtype: 'container',
            region: 'center',
            id: 'pageContainer',
            layout: 'fit'
        }
    ],
    listeners: {
        afterrender: 'onViewportAfterRender'
    }

});