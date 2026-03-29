
interface ProductProtoype{
    Product clone();
}

class Product implements ProductProtoype{
    int id;
    int load_images;
    String give_meta_data;
    String db_loading;
    
    public Product(int id){
        this.id = id;
        this.load_images = 23113123;
        this.give_meta_data = "daskdsaldjkkdj";
        this.db_loading = "dasdjsajdkasldaskdljalkdjakldjksajdajdlakdjal";
    }

    //
    private Product(Product baseProduct){
        this.load_images = baseProduct.load_images;
        this.give_meta_data = baseProduct.give_meta_data;
        this.db_loading = baseProduct.db_loading;
    }

    public Product clone(){
        return new Product(this);
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", load_images=" + load_images + ", give_meta_data=" + give_meta_data
                + ", db_loading=" + db_loading + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    
}


public class Prototype_design_class_demo {
    public static void main(String[] args) {
        Product base = new Product(100);
        Product cloned = base.clone();
        System.out.println(base.toString());
        System.out.println(cloned.toString());

    }
}
