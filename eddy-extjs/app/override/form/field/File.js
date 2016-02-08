Ext.define('Rai.override.form.field.File', {
    override: 'Ext.form.field.File',

    initComponent: function () {
        var me = this;
        me.fileArray = [];
        me.buttonOnly = true;

        me.on('render', function () {
            me.fileInputEl.set({ multiple: true });
        });

        me.callParent(arguments);
        me.getFiles = function(){
            console.log("Richiamato getFiles");
            return me.fileArray;
        };
    },

    onFileChange: function (button, e, value) {

        this.duringFileSelect = true;


        var me = this,
            upload = me.fileInputEl.dom,
            files = upload.files,
            names = [];

        if (files) {
            for (var i = 0; i < files.length; i++){
                names.push(files[i].name);
                me.fileArray.push(files);
            }
            value = names.join(', ');
        }

        Ext.form.field.File.superclass.setValue.call(this, value);
        delete this.duringFileSelect;
    }


});
