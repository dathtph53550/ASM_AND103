const express = require('express');

const app = express();

const port = 3000;

const bodyParser = require("body-parser");
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
});

const COMMON = require('./COMON');

const uri = COMMON.uri;

const mongoose = require('mongoose');

const ProductModel = require('./productModel');
const CategoryModel = require('./categoryModel');

const apiMobile = require('./api');
app.use('/api', apiMobile);

app.get('/list', async (req, res) => {
    await mongoose.connect(uri);

    let cars = await ProductModel.find().populate({ path: 'category', strictPopulate: false });
    console.log(cars);

    res.send(cars);

})

app.post('/add', async (req, res) => {
    await mongoose.connect(uri);

    let car = req.body;

    console.log(car)

    let kq = await ProductModel.create(car);
    console.log(kq);

    let cars = await ProductModel.find();

    res.send(cars);

})

app.delete('/delete/:id', async (req, res) => {
        await mongoose.connect(uri);

        let id = req.params.id;
        console.log(id);
        await ProductModel.deleteOne({_id:id});

        let product = await ProductModel.find();
        res.send(product);    
}) 


app.put('/update/:id', async (req, res) => {
    await mongoose.connect(uri);
    const id = req.params.id;
    console.log(id);
    let newProduct = req.body;

    console.log(newProduct);
    await ProductModel.updateOne({ _id: id }, { $set: newProduct });
    let SanPhams = await ProductModel.find({});
    
    // res.send(SanPhams);
    res.json(SanPhams);
})