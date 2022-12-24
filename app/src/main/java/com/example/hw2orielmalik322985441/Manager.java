package com.example.hw2orielmalik322985441;
import java.util.ArrayList;

public class Manager {


        private String name = "";
        private ArrayList<Integer> Score = new ArrayList<>();

        public Manager() {
        }

    public void setScore(ArrayList<Integer> score) {
        Score = score;
    }

    public ArrayList<Integer> getScore() {
        return Score;
    }

    @Override
        public String toString() {
            return "Playlist{" +
                    "Score=" + String.valueOf(Score);


        }





}

