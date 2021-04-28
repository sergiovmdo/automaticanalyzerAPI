package com.aaa.automaticanalyzer.notifications;

public enum NotificationType {
    ANALYSIS("Nueva analítica disponible", "Ya puedes consultar tu analítica"),
    MEDICATION("Nueva medicación disponible", "Se han realizado cambios en tu medicación"),
    CALENDAR("Nueva cita añadida a tu calendario", "Se ha añadido una nueva cita, consúltala"),
    CHAT("Nuevo mensaje recibido", ""),
    REVISION("Visite su centro de salud", "Su medicación necesita revisión médica, por favor visite su centro de salud");

    private String notificationTitle;
    private String notificationBody;

    private NotificationType(String notificationTitle, String notificationBody) {
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }
}
