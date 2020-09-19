package psy.fit.bstu.secondlab;

public class Calculator {
    double weight;
    double height;
    int age;
    Gender gender;
    PhysActivity activity;

    public double calculate() {
        return Math.ceil(getBMR() * getAMR());
    }

    private double getBMR() {
        if (age > 20 && gender == Gender.Female) {
            return 655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age);
        }
        return 66.473 + (13.7516 * weight) + (5.0033 * height) - (6.775 * age);
    }

    private double getAMR() {
        switch (activity) {
            case None: {
                return 1.2;
            }
            case Regular: {
                return 1.55;
            }
            case Intensive: {
                return 1.725;
            }
        }
        return 1;
    }
}
