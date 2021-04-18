const Items = {
  data() {  
    return {items: null}
  },
  mounted(){
    axios.get('/items').then(response => (this.items = response.data))
  }
}

const app = Vue.createApp(Items)

		
app.component('item_card', {
  props: ['item'],
  template: `
      <td>{{ item.name }}</td>
      <td>{{ item.amount }}</td>
      <td>{{ item.user }}</td>
  `
});

app.mount('#itemList')





