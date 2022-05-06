<?php

namespace App\Http\Controllers\club\pageClub;

use App\Http\Controllers\Controller;
use App\Models\activity;
use Illuminate\Http\Request;
use App\Http\Requests\ActivitiesRequest;

class ActivitiesController extends Controller
{
    public function show($id){
        $activities = activity::where('idClub', $id)->get();
        if(empty($activities)){
            return response()->json(['message' => 'Aucune activité pour ce club'], 404);
        }
        return response()->json($activities, 200);
    }

    public function getActivities(Request $request){
        $club = $request->user()->club()->get('id');
        $activities = activity::where('idClub','=',$club[0]->id)->orderBy('updated_at')->paginate(5);
        if (sizeof($activities) > 0)
            return response()->json(
                $activities,
                200
            );
        else
            return response()->json([
                "Aucun activité"
            ], 404);
    }
    public function createActivities(ActivitiesRequest $request){
        $paragraphe = $request->input('paragraphe');
        $club = $request->user()->club()->get('id');

        $idClub = $club[0]->id;

        $newActivity = array(
            "paragraphe" => $paragraphe,
            "idClub" => $idClub
        );

        $createNewActivity = activity::create($newActivity);

        if ($createNewActivity) {
            return response()->json([
                'data' => [
                    'message' => 'Activity created',
                    'id' => $createNewActivity->id,
                    'attributes' => $createNewActivity
                ]
            ], 201);
        }
        return "Activity created";
    }

    public function deleteActivity($idActivity){

        $activity = activity::find($idActivity);

        if ($activity){
            $activity->delete();
            return response()->json([
                'type' => 'Activity',
                'message' => 'Activity Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Activity',
                'message' => 'Activity non trouvée'
            ], 404);
        }
    }

    public function updateActivity($idActivity, ActivitiesRequest $request){

        $activity = activity::find($idActivity);
        if ($activity) {

            $activity->paragraphe = $request->input('paragraphe') ? $request->input('paragraphe') : $activity->paragraphe;
            $activity->save();
            return response()->json([
                'message' => 'Activity mis à jour',
                'id' => $activity->id,
                'attributes' => $activity
            ], 201);
        } else {
            return response()->json([
                "Activity non trouvée"
            ], 404);
        }
    }
}
