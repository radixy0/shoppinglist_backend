const Items = {
  data() {
  
    return {
      items: [
      ]
    }
  },
  mounted() {
    axios
    .get('/items')
    .then(response => (items = response.data))
   }
}

const app = Vue.createApp(Items)

		
app.component('item_card', {
  props: ['item'],
  template: `
    <tr>
      <td>{{ item.name }}</td>
      <td>{{ item.amount }}</td>
      <td>{{ item.user }}</td>
    </tr>
  `
});

app.mount('#itemList')





