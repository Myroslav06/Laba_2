
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws Exception {
        //System.out.println(3317%3); //2 - StringBuffer
        //System.out.println(3317%17); //2 - Знайти таке слово в першому реченні заданого тексту, якого немає в жодному з наступних.
        String str = "Навесні ліс оживає по-особливому. Молоді листочки на деревах, ніби оксамитові зелені очі, дивляться на світ. Сонце проникає крізь молодий листок, і земля вкривається яскравими квітами.\r\n"
                + //
                "Скрізь чути спів пташок. Кожна пташка співає свою пісню, і весь ліс наповнюється мелодією. Чути, як білка стрибає з гілки на гілку, а десь далеко чути, як лісник рубає дрова. Усе навколо живе, дихає та співає.\r\n"
                + //
                "Ліс є домом для багатьох тварин і птахів. Він дає нам чисте повітря і прохолоду в спеку. Ліс — це справжній скарб, який потрібно берегти.\r\n";
        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < sb.length(); i++) {
            char originChar = sb.charAt(i);
            sb.setCharAt(i, Character.toLowerCase(originChar));
        }

        int indxOfLowestEnd = 0;
        ArrayList<Integer> endPoints = new ArrayList<Integer>(3);
        int endOfSentence1 = sb.indexOf(".");
        int endOfSentence2 = sb.indexOf("?");
        int endOfSentence3 = sb.indexOf("!");
        endPoints.add(endOfSentence1);
        endPoints.add(endOfSentence2);
        endPoints.add(endOfSentence3);
        endPoints.sort(null);
        for (Integer i : endPoints) {
            if (i > 0) {
                indxOfLowestEnd = i;
                break;
            }
        }

        StringBuffer firstSentence = new StringBuffer(sb.substring(0, indxOfLowestEnd + 1));
        StringBuffer restSentence = new StringBuffer(sb.substring(indxOfLowestEnd + 1, sb.length()));

        // Прибираємо розділові знаки в першому речені
        for (int i = 0; i < firstSentence.length(); i++) {
            char c = firstSentence.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                if (c == '-') {
                    continue;
                }
                firstSentence.setCharAt(i, ' ');
            }
        }
        // Прибираємо розділові знаки в решті речень
        for (int i = 0; i < restSentence.length(); i++) {
            char c = restSentence.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                if (c == '-') {
                    continue;
                }
                restSentence.setCharAt(i, ' ');
            }
        }

        // створюємо масив з словами з першого речення
        ArrayList<StringBuffer> firstSentenceWords = new ArrayList<>();
        int startOfTheWord = 0;
        int endOfTheWord = firstSentence.length();

        for (int i = 0; i < firstSentence.length(); i++) {
            endOfTheWord = firstSentence.length();
            if (firstSentence.charAt(i) == ' ') {
                endOfTheWord = i;
                firstSentenceWords.add(new StringBuffer(firstSentence.substring(startOfTheWord, endOfTheWord)));
                startOfTheWord = i + 1;
            }
        }

        ArrayList<StringBuffer> restSentenceWords = new ArrayList<>();
        startOfTheWord = 0;
        endOfTheWord = restSentence.length();

        for (int i = 0; i < restSentence.length(); i++) {
            endOfTheWord = restSentence.length();
            if (restSentence.charAt(i) == ' ') {
                endOfTheWord = i;
                restSentenceWords.add(new StringBuffer(restSentence.substring(startOfTheWord, endOfTheWord)));
                startOfTheWord = i + 1;
            }
        }
        boolean key;
        boolean equals = false;
        ArrayList<StringBuffer> unicElements = new ArrayList<>();

        for (StringBuffer a : firstSentenceWords) {
            boolean found = false;
            for (StringBuffer b : restSentenceWords) {
                if (a.length() == b.length()) {
                    boolean equal = true;
                    for (int i = 0; i < a.length(); i++) {
                        if (a.charAt(i) != b.charAt(i)) {
                            equal = false;
                            break;
                        }
                    }
                    if (equal) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                boolean alreadyAdded = false;
                for (StringBuffer c : unicElements) {
                    if (c.length() == a.length()) {
                        boolean equal = true;
                        for (int i = 0; i < c.length(); i++) {
                            if (c.charAt(i) != a.charAt(i)) {
                                equal = false;
                                break;
                            }
                        }
                        if (equal) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                }
                if (!alreadyAdded) {
                    unicElements.add(a);
                }
            }
        }

        System.out.println(unicElements);
    }
}
