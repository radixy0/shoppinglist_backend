const Items = {
  data() {  
    return {items: null}
  },
  methods:{
    refresh: function(){
      console.log("refresh")
      axios.get('/items').then(response => (this.items = response.data));
    },
    submitNewItem: function(){
      axios.post('/items',{
        name: document.getElementById("textbox_newItem").value,
        amount: document.getElementById("textbox_newAmount").value,
        user: "test"
      });
      this.refresh();
    }
  },
  mounted(){
    this.refresh();
  }
}

const app = Vue.createApp(Items)

		
app.component('item_card', {
  props: ['item'],
  methods: {
    deleteItem: function(){
      console.log("delete: "+ this.item.id);
      //axios.delete('/items/'+this.item.id);
      this.$el.parentNode.removeChild(this.$el)
      
      //this.$root.refresh();
    }
  },
  
  template: `
      <td>{{ item.id }}</td>
      <td>{{ item.name }}</td>
      <td>{{ item.amount }}</td>
      <td>{{ item.user }}</td>
      <td><button v-on:click="deleteItem()">X</button></td>
  `
});

app.mount('#itemList')





