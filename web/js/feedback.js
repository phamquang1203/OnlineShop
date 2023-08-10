function search(page){
  var Page = page; console.log(Page);
    var userName = document.getElementById("userName").value;
    var productName = document.getElementById("productName").value;
    const starEle = document.getElementsByClassName("star");
    const sortEle =document.getElementsByClassName("sort");
    var star = 0;
    var sort = 0;
    for (let i = 0; i < starEle.length; i++) {
      if (starEle[i].checked) {
        star = starEle[i].value;
        break; // Exit the loop once the checked element is found
      }
    }
    for (let i = 0; i < sortEle.length; i++) {
      if (sortEle[i].checked) {
        sort = sortEle[i].value;
        break; // Exit the loop once the checked element is found
      }
    }

    $.ajax({
      url: "/ShopOnline/searchFeedbackmkt",
      type: "get", //send it through get method
      data: {
        UserName: userName,
        ProductName: productName,
        Star: star,
        Sort: sort,
        Page: Page
      },
      success: function (data) {
          var row = document.getElementById("right");
          row.innerHTML = data;
      },
      error: function (xhr) {
          //Do Something to handle error
      }
  });
}

function getDetail(ID){
  $.ajax({
    url: "/ShopOnline/feedbackDetailmkt",
    type: "get", //send it through get method
    data: {
      ID: ID
    },
    success: function (data) {
        var row = document.getElementById("modal-body");
        row.innerHTML = data;
    },
    error: function (xhr) {
        console.log('loi');
    }
});
}

function setDone(ID){
  $.ajax({
    url: "/ShopOnline/changeStatusmkt",
    type: "get", //send it through get method
    data: {
      ID: ID
    },
    success: function (data) {
        var row = document.getElementById("modal-body");
        row.innerHTML = data;
        var row1 = document.querySelector('td#statuss-'+ID);
        row1.innerHTML =  '<div style="color: green;">Done</div>';
    },
    error: function (xhr) {
        console.log('loi');
    }
});
}
