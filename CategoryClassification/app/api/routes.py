import logging
from typing import Any
from bson import ObjectId
from flask import Blueprint, request, jsonify, Response

from app.prediction.processing import predict_clothing
from app.exceptions.illegal_argument_exception import IllegalArgumentException
from app.model.predicted_category import PredictedCategory

api = Blueprint('api', __name__)
logger = logging.getLogger(__name__)


@api.route('/predict', methods=['POST'])
def predict():
    if 'file' not in request.files:
        logger.error('No file part')
        raise IllegalArgumentException('No file part')

    file = request.files['file']

    if file.filename == '':
        logger.error('No selected file')
        raise IllegalArgumentException('No selected file')

    try:
        # Perform prediction
        predicted_category = predict_clothing(file)

        return jsonify(PredictedCategory(predicted_category).__dict__)

    except Exception as e:
        logger.error('Error while predicting category: %s', str(e))
        raise IllegalArgumentException(str(e))