package pt.ipleiria.estg.dei.ei.esoft;

import java.util.Arrays;

public enum EscalaoEtario {
    Varios, Bejamins, Infantis, Iniciados, Juvenis, Cadetes, Juniores, Sub23, Seniores, Veteranos;

    public static String[] getValues(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public int[] getIdade (EscalaoEtario escalaoEtario){
        int[] array = new int[2];
        switch (escalaoEtario){
            case Bejamins -> {
                array[0] = 8;
                array[1] = 10;
                return array;
            }
            case Infantis -> {
                array[0] = 11;
                array[1] = 11;
                return array;
            }
            case Iniciados -> {
                array[0] = 12;
                array[1] = 12;
                return array;
            }
            case Juvenis -> {
                array[0] = 13;
                array[1] = 14;
                return array;
            }
            case Cadetes -> {
                array[0] = 15;
                array[1] = 17;
                return array;
            }
            case Juniores -> {
                array[0] = 18;
                array[1] = 20;
                return array;
            }
            case Sub23 -> {
                array[0] = 21;
                array[1] = 22;
                return array;
            }
            case Seniores -> {
                array[0] = 21; //idade minima
                return array;
            }
            case Veteranos -> {
                array[0] = 30; //idade minima
                return array;
            }
        };

        return array;
    }
}
