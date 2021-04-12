import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from 'vuetify/lib/util/colors'
Vue.use(Vuetify);

export default new Vuetify({
    theme:{
        themes:{
            light:{
                primary: colors.indigo.darken1,
                secondary: colors.purple.darken4,
                accent: colors.orange.lighten1,
                error: '#FF5252',
                info: '#2196F3',
                success: '#4CAF50',
                warning: '#FFC107',
                potato: colors.yellow.lighten1
            },
            dark:{
                primary: colors.red.lighten1,
                secondary: colors.purple.lighten2,
                accent: colors.orange.lighten1,
                error: '#FF5252',
                info: '#2196F3',
                success: '#4CAF50',
                warning: '#FFC107',
                potato: colors.yellow.lighten1
            }
        }
    }
});
