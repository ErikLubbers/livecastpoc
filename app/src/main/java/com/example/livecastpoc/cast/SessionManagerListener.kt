package com.example.livecastpoc.cast

import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener

class SessionManagerListenerImpl : SessionManagerListener<CastSession> {
    override fun onSessionStarted(session: CastSession, sessionId: String) {}
    override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {}
    override fun onSessionEnded(session: CastSession, error: Int) {}
    override fun onSessionEnding(p0: CastSession) {}
    override fun onSessionResumeFailed(p0: CastSession, p1: Int) {}
    override fun onSessionResuming(p0: CastSession, p1: String) {}
    override fun onSessionStartFailed(p0: CastSession, p1: Int) {}
    override fun onSessionStarting(p0: CastSession) { }
    override fun onSessionSuspended(p0: CastSession, p1: Int) {}
}