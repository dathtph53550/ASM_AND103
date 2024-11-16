const mongoose = require('mongoose');

const ProductSchema = new mongoose.Schema({
    productName: {
        type: String,
        require:true
    },
    Description: {
        type: String
    },
    price: {
        type: Number
    },
    CateID:{
        type:mongoose.Schema.Types.ObjectId,ref:'categoryModel'
    },
    image:{
        type:String
    }
});

const ProductModel = new mongoose.model('product', ProductSchema);

module.exports = ProductModel;