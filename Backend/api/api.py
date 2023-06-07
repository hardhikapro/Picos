from flask import Flask, request, jsonify
from db import get_list, add_list
import tensorflow as tf
import pandas as pd 

app = Flask(__name__)

# Load the trained model
model = tf.keras.models.load_model('modelv01.h5')

# Load the data into a pandas DataFrame
df = pd.read_excel('PiCOS_cleaned_data_engineered.xlsx')

# Define the column names
features = ['BMI', 'Blood Group', 'Cycle(R/I)', 'Pregnant(Y/N)',
            'No. of aborptions', 'Waist:Hip Ratio', 'Weight gain(Y/N)', 'hair growth(Y/N)',
            'Skin darkening (Y/N)', 'Hair loss(Y/N)', 'Pimples(Y/N)', 'Fast food (Y/N)',
            'Reg.Exercise(Y/N)', 'age_category', 'marriage_category']

# Define the category labels
category_labels = {0: 'Non-PCOS', 1: 'PCOS'}

def predict_pcos(model, data, threshold=0.5):
    # Preprocess the input data
    input_data = data[features]  # Select the relevant columns for prediction

    # Make predictions
    predictions = model.predict(input_data)

    # Convert the predictions to categories based on the threshold
    predicted_categories = [category_labels[int(prediction[0] >= threshold)] for prediction in predictions]

    # Return the predicted categories
    return predicted_categories

# Accept user input for each feature
user_data = {}
for feature in features:
    value = input(f"Enter value for {feature}: ")
    user_data[feature] = float(value) if '.' in value else int(value)

# Create a DataFrame from the user input
user_df = pd.DataFrame(user_data, index=[0])

# Make predictions using the user input
predictions = predict_pcos(model, user_df)

for prediction in predictions:
    print("Prediction:", prediction)

if __name__ == '__main__':
    app.run(debug=True)