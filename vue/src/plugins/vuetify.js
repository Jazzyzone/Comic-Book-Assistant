import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from 'vuetify/lib/util/colors'
Vue.use(Vuetify);

export default new Vuetify({
    theme:{
        themes:{
            light:{
                primary: colors.blue.lighten1,
                secondary: colors.purple.lighten2,
                accent: colors.orange.lighten1
            }
        }
    }
});
