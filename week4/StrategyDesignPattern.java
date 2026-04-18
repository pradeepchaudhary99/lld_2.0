
interface SortingStrategy{
    void sort(int [] nums);
}


class MergeSort implements SortingStrategy{

    @Override
    public void sort(int[] nums) {
        System.out.println("Sorting using MergeSort");
    }

}

class QuickSort implements SortingStrategy{

    @Override
    public void sort(int[] nums) {
        System.out.println("Sorting using QuickSort");
    }
    
}

class HeapSort implements SortingStrategy{
    @Override
    public void sort(int[] nums) {
        System.out.println("Sorting using HeapSort");
    }   
}

class SortLibrary{
    private SortingStrategy sortingStrategy;
    public SortLibrary(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy; 
    }
    void changeStrategy(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy;
        System.out.println("Strategy is changed");
    }

    void sort(int [] nums){
        sortingStrategy.sort(nums);
    }
}



// Factory to get the objects



class Factory{

    // qiu
    // sada

}
public class StrategyDesignPattern {
    public static void main(String[] args) {
        SortLibrary sortLibrary = new SortLibrary(Factory.getInstance("QuickSort"));
        int [] num = {4,2,12,3};
        sortLibrary.sort(num);
        sortLibrary.changeStrategy(new QuickSort());
        sortLibrary.sort(num);

    }
}
