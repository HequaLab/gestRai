/*
 * File: app/view/frmLogin.js
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

Ext.define('Rai.view.frmLogin', {
    extend: 'Ext.container.Container',
    alias: 'widget.frmLogin',

    requires: [
        'Rai.view.frmLoginViewModel',
        'Rai.view.frmLoginViewController',
        'Ext.panel.Panel',
        'Ext.form.field.Text',
        'Ext.button.Button',
        'Ext.form.field.Checkbox'
    ],

    controller: 'frmlogin',
    viewModel: {
        type: 'frmlogin'
    },
    height: 666,
    id: 'frmLogin',
    width: 1069,

    layout: {
        type: 'hbox',
        align: 'middle',
        pack: 'center'
    },
    items: [
        {
            xtype: 'panel',
            height: 241,
            width: 495,
            layout: 'absolute',
            title: 'Login',
            items: [
                {
                    xtype: 'textfield',
                    x: 110,
                    y: 50,
                    tabIndex: 1,
                    id: 'userNameTxt',
                    width: 300,
                    fieldLabel: 'User',
                    listeners: {
                        render: 'onUserNameTxtRender'
                    }
                },
                {
                    xtype: 'textfield',
                    x: 110,
                    y: 80,
                    tabIndex: 2,
                    id: 'pwdTxt',
                    width: 300,
                    fieldLabel: 'Pwd',
                    inputType: 'password'
                },
                {
                    xtype: 'button',
                    x: 110,
                    y: 120,
                    tabIndex: 3,
                    id: 'loginButton',
                    width: 300,
                    text: 'Login',
                    listeners: {
                        click: {
                            fn: 'onLoginClick',
                            element: ''
                        }
                    }
                },
                {
                    xtype: 'checkboxfield',
                    x: 110,
                    y: 160,
                    tabIndex: 4,
                    id: 'cookieLogin',
                    fieldLabel: 'Ricordami',
                    labelSeparator: '',
                    boxLabel: '',
                    checked: true,
                    listeners: {
                        change: 'onCookieLoginChange'
                    }
                }
            ]
        }
    ],
    listeners: {
        afterrender: 'onFrmLoginAfterRender'
    }

});