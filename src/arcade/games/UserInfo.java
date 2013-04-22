package arcade.games;

import java.awt.Image;

/**
 * UserInfo: work as a package when/in:
 * 1. Communication between the UserProfile in view and the back-end model
 * 2. Game Programmers want to show user information in their game view when running the game. 
 * @author David Liu
 *
 */
public class UserInfo implements User{


	/**
	 * Data
	 */
	private String myUserName;
	private Image myAvatar;
	private String myName; 
	private int myAge;
	private String myHometown;
	private String mySchool;
	private String[] myHobbies;
	private String myFavoriteColor;
	private String myFavoriteFood;


	/**
	 * return the user name
	 */
	@Override
	public String getUsername() {
		return myUserName;
	}

	/**
	 * return the avatar (user image) 
	 */
	@Override
	public Image getAvatar() {
		return myAvatar;
	}

	/**
	 * update the user's avatar/image
	 * @param newavatar
	 */
	public void changeAvatar(Image newavatar){
		this.myAvatar = newavatar;
	}

	/**
	 * Return the "actual" name of the user
	 * @return
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Update the "actual" name of the user
	 * @param name
	 */
	public void changeName(String name) {
		this.myName = name;
	}

	/**
	 * return the age of the user
	 * @return
	 */
	public int getAge() {
		return myAge;
	}

	/**
	 * update the age of the user
	 */
	public void changeAge(int age) {
		this.myAge = age;
	}

	/**
	 * return the hometown of the user
	 * @return
	 */
	public String getHometown() {
		return myHometown;
	}

	/**
	 * update the hometown of the user
	 * @param hometown
	 */
	public void changeHometown(String hometown) {
		this.myHometown = hometown;
	}

	/**
	 * return the school of the user
	 * @return
	 */
	public String getSchool() {
		return mySchool;
	}

	/**
	 * update the school of the user
	 * @param school
	 */
	public void changeSchool(String school) {
		this.mySchool = school;
	}

	/**
	 * return the hobbies of the user
	 * @return
	 */
	public String[] getHobbies() {
		return myHobbies;
	}

	/**
	 * update the hobbies of the user
	 */
	public void changeHobbies(String[] hobbies) {
		this.myHobbies = hobbies;
	}

	/**
	 * return the favorite color of the user
	 * @return
	 */
	public String getFavoriteColor() {
		return myFavoriteColor;
	}

	/**
	 * update the favorite color of the user
	 * @param favoriteColor
	 */
	public void changeFavoriteColor(String favoriteColor) {
		this.myFavoriteColor = favoriteColor;
	}

	/**
	 * return the favorite food of the user
	 * @return
	 */
	public String getFavoriteFood() {
		return myFavoriteFood;
	}

	/**
	 * update the favorite food of the user
	 * @param favoriteFood
	 */
	public void changeFavoriteFood(String favoriteFood) {
		this.myFavoriteFood = favoriteFood;
	}
}
