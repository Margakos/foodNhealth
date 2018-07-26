-- Insert Initial Data to the System

/* Cuisines */
INSERT INTO foodnhealth.Cuisine (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (1, '2018-07-26 00:01:00', '', true, false, 'Basic', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Cuisine (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (2, '2018-07-26 00:01:00', '', true, false, 'Vegetarian', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Cuisine (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (3, '2018-07-26 00:01:00', '', true, false, 'Vegan', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Cuisine (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (4, '2018-07-26 00:01:00', '', true, false, 'Mediterranean', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Cuisine (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (5, '2018-07-26 00:01:00', '', true, false, 'Asian', '2018-07-26 00:01:00');

/* Recipe Categories */
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (1, '2018-07-26 00:01:00', '', true, false, 'Μικρά γεύματα', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (2, '2018-07-26 00:01:00', '', true, false, 'Κυρίως γεύματα', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (3, '2018-07-26 00:01:00', '', true, false, 'Ροφήματα', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (4, '2018-07-26 00:01:00', '', true, false, 'Σνακ', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (5, '2018-07-26 00:01:00', '', true, false, 'Συνοδευτικά', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (6, '2018-07-26 00:01:00', '', true, false, 'Φρούτα', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.RecipeCategory (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (7, '2018-07-26 00:01:00', '', true, false, 'Σαλάτες', '2018-07-26 00:01:00');

/* SuperMarket */
INSERT INTO foodnhealth.Supermarket (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (1, '2018-07-26 00:01:00', '', true, false, 'AB', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Supermarket (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (2, '2018-07-26 00:01:00', '', true, false, 'Masoutis', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Supermarket (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (3, '2018-07-26 00:01:00', '', true, false, 'Lidl', '2018-07-26 00:01:00');

/* Food Category Core Types */
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (1, '2018-07-26 00:01:00', '', true, false, 'Γαλακτοκομικά', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (2, '2018-07-26 00:01:00', '', true, false, 'Λαχανικά', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (3, '2018-07-26 00:01:00', '', true, false, 'Φρούτα κ Χυμοί', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (4, '2018-07-26 00:01:00', '', true, false, 'Αμυλούχα', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (5, '2018-07-26 00:01:00', '', true, false, 'Κρέατα', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (6, '2018-07-26 00:01:00', '', true, false, 'Λίποι', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (7, '2018-07-26 00:01:00', '', true, false, 'Ελεύθερα', '2018-07-26 00:01:00');

/* Food Category Sub Types */
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (1, '2018-07-26 00:01:00', '', true, false, 'Πλήρες', '2018-07-26 00:01:00', 1);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (2, '2018-07-26 00:01:00', '', true, false, 'Ημίπαχα', '2018-07-26 00:01:00', 1);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (3, '2018-07-26 00:01:00', '', true, false, 'Άπαχα', '2018-07-26 00:01:00', 1);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (4, '2018-07-26 00:01:00', '', true, false, 'Λαχανικά', '2018-07-26 00:01:00', 2);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (5, '2018-07-26 00:01:00', '', true, false, 'Φρούτα', '2018-07-26 00:01:00', 3);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (6, '2018-07-26 00:01:00', '', true, false, 'Χυμοί', '2018-07-26 00:01:00', 3);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (7, '2018-07-26 00:01:00', '', true, false, 'Ψωμί', '2018-07-26 00:01:00', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (8, '2018-07-26 00:01:00', '', true, false, 'Δημητριακά', '2018-07-26 00:01:00', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (9, '2018-07-26 00:01:00', '', true, false, 'Ρύζι', '2018-07-26 00:01:00', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (10, '2018-07-26 00:01:00', '', true, false, 'Σούπες', '2018-07-26 00:01:00', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (11, '2018-07-26 00:01:00', '', true, false, 'Όσπρια', '2018-07-26 00:01:00', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (12, '2018-07-26 00:01:00', '', true, false, 'Αμυλούχα λαχανικά', '2018-07-26 00:01:00', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (13, '2018-07-26 00:01:00', '', true, false, 'Χαμηλό σε λιπαρά κρέας', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (14, '2018-07-26 00:01:00', '', true, false, 'Χαμηλό σε λιπαρά τυρί', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (15, '2018-07-26 00:01:00', '', true, false, 'Χαμηλό σε λιπαρά ψάρι', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (16, '2018-07-26 00:01:00', '', true, false, 'Μέσο σε λιπαρά κρέας', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (17, '2018-07-26 00:01:00', '', true, false, 'Μέσο σε λιπαρά τυρί', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (18, '2018-07-26 00:01:00', '', true, false, 'Μέσο σε λιπαρά ψάρι', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (19, '2018-07-26 00:01:00', '', true, false, 'Μέσο σε λιπαρά αυγό', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (20, '2018-07-26 00:01:00', '', true, false, 'Υψηλό σε λιπαρά κρέας', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (21, '2018-07-26 00:01:00', '', true, false, 'Υψηλό σε λιπαρά τυρί', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (22, '2018-07-26 00:01:00', '', true, false, 'Υψηλό σε λιπαρά ψάρι', '2018-07-26 00:01:00', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (23, '2018-07-26 00:01:00', '', true, false, 'Κορεσμένα', '2018-07-26 00:01:00', 6);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (24, '2018-07-26 00:01:00', '', true, false, 'Μονοακόρεστα', '2018-07-26 00:01:00', 6);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (25, '2018-07-26 00:01:00', '', true, false, 'Μολυακόρεστα', '2018-07-26 00:01:00', 6);
INSERT INTO foodnhealth.FoodCategorySubType (id, createDate, description, isActive, isDeleted, title, updateDate, foodCategoryCoreType_id) VALUES (26, '2018-07-26 00:01:00', '', true, false, 'Ελεύθερα', '2018-07-26 00:01:00', 7);

/* Food Category Core Types */
INSERT INTO foodnhealth.Allergy (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (1, '2018-07-26 00:01:00', '', true, false, 'Ξηροί Καρποί', '2018-07-26 00:01:00');
INSERT INTO foodnhealth.Allergy (id, createDate, description, isActive, isDeleted, title, updateDate) VALUES (2, '2018-07-26 00:01:00', '', true, false, 'Φράουλες', '2018-07-26 00:01:00');

/* Users */
INSERT INTO foodnhealth.Nutritionist (id, createDate, description, isActive, isDeleted, title, updateDate, comments, email, firstName, gender, lastName, password)
VALUES (1, '2018-07-26 00:01:00', 'The first ever admin', true, false, null, '2018-07-26 00:01:00', 'First admin ever', 'admin@foodnhealth.gr', 'Food', null, 'Health', '123456');