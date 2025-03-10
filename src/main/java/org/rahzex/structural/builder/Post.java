package org.rahzex.structural.builder;

import lombok.Getter;

@Getter
public class Post {
    private final String title;
    private final String text;
    private final String category;

    private Post(Builder builder) {
        this.title = builder.title;
        this.text = builder.text;
        this.category = builder.category;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String text;
        private String category;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
