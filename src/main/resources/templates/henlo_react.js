'use strict';

const e = React.createElement;

class ItemTable extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            itemList: [],
            itemFieldValue: "new item",
            amountFieldValue: 1
        };
        this.refresh();
        
        this.handleAdd=this.handleAdd.bind(this);
        this.handleDelete=this.handleDelete.bind(this);
        this.handleItemFieldChange=this.handleItemFieldChange.bind(this);
        this.handleAmountFieldChange=this.handleAmountFieldChange.bind(this);
        this.printInfo=this.printInfo.bind(this);
    }
    refresh(){
        console.log("refresh");
        axios.get('/items').then(response => {this.setItemList(response.data)});
    }
    setItemList(newList){
        this.setState({itemList: newList});
    }
    handleDelete(id){
        console.log("handling delete: "+id);
        axios.delete('/items/'+id).then(
            response => {this.setItemList(response.data)}
        );
    }
    handleAdd(){
        console.log("handling add");
        axios.post('/items',{
            name: this.state.itemFieldValue,
            amount: this.state.amountFieldValue,
            user: "test"
          }).then(
              response => {this.setItemList(response.data)}
          );
    }
    handleItemFieldChange(event){
        this.setState({ itemFieldValue: event.target.value });
    }
    handleAmountFieldChange(event){
        this.setState({ amountFieldValue: event.target.value });
    }
    printInfo(){
        console.log("state:");
        console.log(this.state);
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
                                e(ItemRow, {item:listEntry, key:listEntry.id, parent:this},null)
                            )
                        )
                    ),
                    e("tfoot",null,
                        e("tr",null,
                            e("td",null,""),
                            e("td",null,
                                e("input",{type:"text", value: this.state.itemFieldValue, onChange: this.handleItemFieldChange},null)
                            ),
                            e("td",null,
                                e("input",{type:"number", value: this.state.amountFieldValue, onChange: this.handleAmountFieldChange},null)
                            ),
                            e("td",null,""),
                            e("td",null,
                                e("button",{onClick: this.handleAdd},"Add")
                            )
                        )
                    )
                )
            )                       
        );
    }
}

class ItemRow extends React.Component { //props: item, parent
    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete(id){
        this.props.parent.handleDelete(this.props.item.id);
    }
  
    render() {
    let item=this.props.item
    return e('tr',null,
        e('td',null,item.id),
        e('td',null,item.name),
        e('td',null,item.amount),
        e('td',null,item.user),
        e('td',null,
            e('button',{onClick: this.handleDelete},"X")
        )
    )
  }
}

const domContainer = document.querySelector('#react_itemTable');
ReactDOM.render(e(ItemTable), domContainer);