package linalg;


abstract class SimpleVector implements IVector{
    public double dot(IVector vec){
        checkDim(vec);
        double sum = 0.0;

        for (int i=0; i < getDim(); i++){
            sum += this.get(i) * vec.get(i);
        }
        return sum;
    }

    public boolean equals(IVector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (Math.abs(this.get(i) - vec.get(i)) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= val)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(IVector vec){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= vec.get(i))
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > val)
                return false;
        }
        return true;
    }
    public boolean isSmallerOrEqualThan(IVector vec){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > vec.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= val)
                return false;
        }
        return true;
    }

    public boolean isLargerThan(IVector vec){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <=vec.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < val)
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(IVector vec){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < vec.get(i))
                return false;
        }
        return true;
    }

    public double norm(){
        double sum = 0.0;
        for (int i=0; i < getDim(); i++){
            sum += this.get(i) * this.get(i);
        }
        return Math.sqrt(sum);
    }

    public double[] toArray(){
        double[] res = new double[(int) getDim()];
        for (int i=0; i < getDim(); i++)
            res[i] = this.get(i);
        return res;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (int i=0; i < getDim(); i++) {
            builder.append(this.get(i));
            builder.append(',');
            builder.append(' ');
        }
        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);
        builder.append('}');
        return builder.toString();
    }
}

