function addToOrder(id) { 
   var num =  document.getElementById("number").value; 
   $.get("http://localhost:8080/SampleFoodStore-V3/add/"+id+"/"+num);
   alert("成功加入订单");
}

function add() {
   var num = parseInt(document.getElementById("number").value);
   if (num < 99) {
      document.getElementById("number").value = ++num;
   }
}

function sub() {
   var num = parseInt(document.getElementById("number").value);
   if (num > 1) {
      document.getElementById("number").value = --num;
   }
}

function delcfm() {
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
}

