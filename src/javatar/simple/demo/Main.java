package javatar.simple.demo;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.LinkedList;

public class Main {
  private final static HashMap<Character, Integer> romanize = new HashMap<>();
  static {
    romanize.put('I', 1);
    romanize.put('V', 5);
    romanize.put('X', 10);
    romanize.put('L', 50);
    romanize.put('C', 100);
    romanize.put('D', 500);
    romanize.put('M', 1000);
  }

  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(System.in);
    System.out.print("Input Roman file: ");
    String filename = sc.nextLine();
    FileReader in = new FileReader(filename);

    BufferedReader br = new BufferedReader(in);
    try {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());

        System.out.println(calculate(line));

        line = br.readLine();
      }
      String everything = sb.toString();

    }
    finally {
      br.close();
    }

    System.out.println("Success process :  " + filename);

  }

  private static String calculate(String line) {
    Pattern p = Pattern.compile("([a-zA-Z]+)|([-+*/])");
    Matcher m = p.matcher(line);
    List<String> tokens = new LinkedList<String>();
    while(m.find())
    {
      String token = m.group();
      tokens.add(token);
    }

    if(tokens.size() == 3) {
      int result = operations(romanToInt(tokens.get(0)), tokens.get(1), romanToInt(tokens.get(2)));
      return intToRoman(result);
    }
    else
    {
      return "This line is incorrect format";
    }
  }

  private static int operations(Integer first, String operator, Integer last) {
    switch(operator) {
      case "+":  return first + last;
      case "-":  return first - last;
      case "*":  return first * last;
      case "/":  return first / last;
      default:   return 0;
    }
  }

  private static int romanToInt(String roman) {
    roman = roman.toUpperCase();

    int result = 0;
    int prev   = 0;
    int temp   = 0;
    char c     = ' ';

    for(int i = roman.length()-1; i>=0 ; i--)
    {
      try {
        c = roman.charAt(i);
        temp = romanize.get(c);
        if(temp < prev)
          result -= temp;
        else
          result += temp;
        prev = temp;
      }
      catch(Exception e) {
        return 0;
      }
    }

    return result;
  }

  private static String intToRoman(Integer input) {
    if(input == 0) {
      return "Cannot Calculate";
    }

    String s = "";
    while (input >= 1000) {
      s += "M";
      input -= 1000;
    }
    while (input >= 900) {
      s += "CM";
      input -= 900;
  }
    while (input >= 500) {
      s += "D";
      input -= 500;
    }
    while (input >= 400) {
      s += "CD";
      input -= 400;
    }
    while (input >= 100) {
      s += "C";
      input -= 100;
    }
    while (input >= 90) {
      s += "XC";
      input -= 90;
    }
    while (input >= 50) {
      s += "L";
      input -= 50;
    }
    while (input >= 40) {
      s += "XL";
      input -= 40;
    }
    while (input >= 10) {
      s += "X";
      input -= 10;
    }
    while (input >= 9) {
      s += "IX";
      input -= 9;
    }
    while (input >= 5) {
      s += "V";
      input -= 5;
    }
    while (input >= 4) {
      s += "IV";
      input -= 4;
    }
    while (input >= 1) {
      s += "I";
      input -= 1;
    }
    return s;
  }
}
