/*******************************************************************************
 * Copyright (c) 2015, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.repository.transport.model;

public class Feedback extends AbstractJSON {

    private int downloads;
    private int favorites;
    private int likes;
    private int rating;
    private int reviews;
    private int tweets;

    public Feedback() {
    }

    /**
     * Copy constructor
     *
     * @param other the object to copy
     */
    public Feedback(Feedback other) {
        this.downloads = other.downloads;
        this.favorites = other.favorites;
        this.likes = other.likes;
        this.rating = other.rating;
        this.reviews = other.reviews;
        this.tweets = other.tweets;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public int getTweets() {
        return tweets;
    }

    public void setTweets(int tweets) {
        this.tweets = tweets;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + downloads;
        result = prime * result + favorites;
        result = prime * result + likes;
        result = prime * result + rating;
        result = prime * result + reviews;
        result = prime * result + tweets;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feedback other = (Feedback) obj;
        if (downloads != other.downloads)
            return false;
        if (favorites != other.favorites)
            return false;
        if (likes != other.likes)
            return false;
        if (rating != other.rating)
            return false;
        if (reviews != other.reviews)
            return false;
        if (tweets != other.tweets)
            return false;
        return true;
    }

}
