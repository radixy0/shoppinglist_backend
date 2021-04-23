'use strict';

const e = React.createElement;

class ItemTable extends React.Component{
    constructor(props) {
        super(props);
        let listFromAPI=this.getItemsFromAPI()       
        console.log(listFromAPI);
        this.state = {
            itemList: listFromAPI
        };
    }
    getItemsFromAPI(){
        console.log("refresh");
        let response = axios.get('/items').then(response => (this.items = response.data.json()));
        return response;
    }
    render(){
        return(
            e(React.Fragment,null,
                e("table",null,
                    e("thead",null,
                        e("tr",null,
                            e("th",null,"ID"),
                            e("th",null,"item"),
                            e("th",null,"amount"),
                            e("th",null,"user"),
                            e("th",null,"")
                        )
                    ),
                    e("tbody",null,
                        this.state.itemList.map(listEntry => (
                                e(ItemRow, {item:listEntry, key:listEntry.id},null)
                            )
                        )
                    )
                )
            )                       
        );
    }
}

class ItemRow extends React.Component { //props: item
  render() {
    let item=this.props.item

    return e('tr',null,
        e('td',null,item.id),
        e('td',null,item.name),
        e('td',null,item.amount),
        e('td',null,item.user)
    )
  }
}

const domContainer = document.querySelector('#react_itemTable');
ReactDOM.render(e(ItemTable), domContainer);