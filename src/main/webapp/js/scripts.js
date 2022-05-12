$('.productsId').each(function( i, productId ) {
    $('#heart-'+productId.value).click(function () {
        if ($(this).hasClass('active')) {
            $.ajax({
                url: "api/favourite",
                method: "get",
                dataType: "json",
                contentType: "application/json",
                data: {
                    productId: productId.value,
                    action: "remove-product",
                }
            }).done(res => {
                $(this).removeClass('active');
                $('#favouriteCounter').text(res[0])
            });
        } else {
            $.ajax({
                url: "api/favourite",
                method: "get",
                dataType: "json",
                contentType: "application/json",
                data: {
                    productId: productId.value,
                    action: "add-product",
                }
            }).done(res => {
                $(this).addClass('active ');
                $('#favouriteCounter').text(res[0])
            });
        }
    });
});

