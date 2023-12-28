import numpy as np
import os
import logging
from datetime import datetime
from tensorflow.keras.applications.mobilenet_v2 import MobileNetV2, preprocess_input, decode_predictions
from app.exceptions.illegal_argument_exception import IllegalArgumentException
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import load_model

MODEL_PATH = 'fashion_mnist_model.h5'
TEMP_IMAGE_PATH = 'TEMP_IMAGE_PATH'
logger = logging.getLogger(__name__)

try:
    model = load_model(MODEL_PATH)
except (OSError, ImportError):
    logger.debug("Model not found. Downloading and saving the model.")
    raise IllegalArgumentException("Model not found. Downloading and saving the model.")

def predict_clothing(file):
    img_rows, img_cols = 28, 28
    image_path = TEMP_IMAGE_PATH + datetime.now().strftime("%Y%m%d-%H%M%S") + ".jpg"
    file.save(image_path)
    logger.info("Image saved to %s", image_path)

    # Load and preprocess the input image
    img = image.load_img(image_path, target_size=(img_rows, img_cols), color_mode="grayscale")
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    img_array /= 255.0

    predictions = model.predict(img_array)
    class_index = np.argmax(predictions)
    os.remove(image_path)
    logger.info("Image removed from %s", image_path)

    # Map the class index to the corresponding label
    class_labels = [
        "TShirt", "Trouser", "Pullover", "Dress", "Coat",
        "Sandal", "Shirt", "Sneaker", "Bag", "Ankle boot"
    ]

    # Decode and print the top predicted label
    predicted_label = class_labels[class_index]
    logger.info("Predicted category: %s", predicted_label)

    return predicted_label