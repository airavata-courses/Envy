from django.conf.urls import include, url
from django.contrib import admin
from PythonService import views
#from rest_framework import routers


#router = routers.DefaultRouter()

#router.register(r^'flights',UserTravelViewSet,'Flights')
urlpatterns = [
    # Examples:
    # url(r'^$', 'Pythonstarter.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^admin/', include(admin.site.urls)),
    url(r'^getiternary/',views.Itenary)
]
