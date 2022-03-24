<?php

namespace App\Http\Controllers;

use App\Models\Materiel;
use Illuminate\Http\Request;
use App\Models\DemandeMateriel;
use App\Models\CategorieMateriel;
use Illuminate\Support\Facades\DB;
use App\Models\DemandeMateriel_Materiel;

class MaterielController extends Controller
{
    public function show($id){
        $Materiel =DemandeMateriel::with('materiel')->where('id','=',$id)->get();


        if(sizeof($Materiel)>0){
        return response()->json(["data"=>$Materiel], 200);
        }
        else
            return response()->json([
                "aucun materiel"
            ], 404);
    }
    public function update($idMateriel, $idDemande){
        $DemandeMateriel = DemandeMateriel_Materiel::where('idDemande','=',$idDemande)->firstOrFail();
        if(isset($DemandeMateriel)){
                $DemandeMateriel->idDemande=$idDemande;
                $DemandeMateriel->idMateriel=$idMateriel;
                $DemandeMateriel->quantité=1;
                $DemandeMateriel->save();
                return response()->json([
                    'message' => 'Update Success',
                    'id' => $DemandeMateriel->id,
                    'attributes' => $DemandeMateriel
            ], 201);
        }
        else return response()->json([
                "aucun materiel"
            ], 404);
    }
    public function showMaterialsOfCategory($id){
        // $Materiel=Materiel::with('role')->whereHas('role',function($a){$a->where('role_id',3);})->get();

        $Materiel = Materiel::where( 'idCategorie','=',$id)->get();
        if(sizeof($Materiel)>0){
            return response()->json(["data"=>$Materiel], 200);
            }
        else
            return response()->json([
                "aucun materiel"
            ], 404);
    }
    public function addMateriel($idMateriel, $idDemande){
        $data = array(
            "idDemande"=> $idDemande,
            "idMateriel"=> $idMateriel,
            "quantité"=>1,
        );
        $DemandeMateriel = DemandeMateriel_Materiel::create($data);
        if ($DemandeMateriel) {
            return response()->json([
                'data' => [
                    'message' => 'Success',
                    'id' => $DemandeMateriel->id,
                    'attributes' => $DemandeMateriel
                ]
            ], 201);
        }
        return "Success";
    }
}
