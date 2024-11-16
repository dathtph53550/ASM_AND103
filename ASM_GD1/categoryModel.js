const mongoose = require('mongoose');

const CategorySchema = new mongoose.Schema({
    cateID: {
        type: Number
    },
    cateName: {
        type: String,
        require:true
    }
});

const CategoryModel = new mongoose.model('category', CategorySchema);

module.exports = CategoryModel;