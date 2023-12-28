import json

class PredictedCategory:
    def __init__(self, category, confidence):
        self.category = category
        self.confidence = confidence