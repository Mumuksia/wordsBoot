package com.muksia.model;

import org.springframework.data.annotation.Id;

public class Scheduler {


    @Id
    private String id;

    private String title;
    private String user;
    private String time;
    private String dayOfWeek;
    private String date;

    public String getTitle() {
        return title;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getDate() {
        return date;
    }

    public static final class SchedulerBuilder {
        private String title;
        private String user;
        private String time;
        private String dayOfWeek;
        private String date;

        public SchedulerBuilder() {
        }

        public static SchedulerBuilder aScheduler() {
            return new SchedulerBuilder();
        }

        public SchedulerBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SchedulerBuilder withUser(String user) {
            this.user = user;
            return this;
        }

        public SchedulerBuilder withTime(String time) {
            this.time = time;
            return this;
        }

        public SchedulerBuilder withDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public SchedulerBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public Scheduler build() {
            Scheduler scheduler = new Scheduler();
            scheduler.title = this.title;
            scheduler.time = this.time;
            scheduler.date = this.date;
            scheduler.user = this.user;
            scheduler.dayOfWeek = this.dayOfWeek;
            return scheduler;
        }
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "title='" + title + '\'' +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
